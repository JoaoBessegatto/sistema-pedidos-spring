package br.com.fatec.Produtos.service;

import br.com.fatec.Produtos.dao.OrderDao;
import br.com.fatec.Produtos.dao.ProdutoDao;
import br.com.fatec.Produtos.dto.request.ItemPedidoRequestDTO;
import br.com.fatec.Produtos.dto.request.OrderRequestDTO;
import br.com.fatec.Produtos.dto.response.OrderResponseDTO;
import br.com.fatec.Produtos.entity.ItemPedido;
import br.com.fatec.Produtos.entity.Order;
import br.com.fatec.Produtos.entity.Produto;
import br.com.fatec.Produtos.exception.EstoqueInsuficienteException;
import br.com.fatec.Produtos.exception.ProdutoNotFoundException;
import ch.qos.logback.core.BasicStatusManager;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    EmailService emailService;

    private final OrderDao dao;
    private final ProdutoDao produtoDao;

    public OrderService(OrderDao dao, ProdutoDao produtoDao) {
        this.dao = dao;
        this.produtoDao = produtoDao;
    }
    @Transactional
    public OrderResponseDTO save (OrderRequestDTO orderRequestDTO) throws MessagingException {
        Order order = new Order();
        order.setData(LocalDateTime.now());

        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(ItemPedidoRequestDTO itemDTO : orderRequestDTO.getItemPedidos()){
            Produto produto = produtoDao.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
            if(produto.getQuantidadeEstoque() < itemDTO.getQuantidade()){
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setOrder(order);

            itens.add(item);
            total = total.add(item.getSubtotal());

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDTO.getQuantidade());
            produtoDao.update(produto);
        }
        order.setItemPedidos(itens);
        order.setValorTotal(total);
        Order orderSaved = dao.save(order);
        try {
            Map<String, Object> dados = getStringObjectMap(itens, order);
            emailService.enviarEmailComTemplate(
                    "bfjoaop@gmail.com",
                    "Confirmação de compra",
                    "email-template",
                    dados
            );
            return new OrderResponseDTO(orderSaved);
        }catch (Exception e){
            throw new MessagingException("erro na criação de email");
            
        }
    }

    private static Map<String, Object> getStringObjectMap(List<ItemPedido> itens, Order order) {
        List<Map<String, Object>> produtos = new ArrayList<>();
        for (ItemPedido item : itens) {
            Map<String, Object> p = new HashMap<>();
            p.put("nome", item.getProduto().getNome());
            p.put("quantidade", item.getQuantidade());
            p.put("preco", item.getPrecoUnitario());
            produtos.add(p);
        }

        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", "João Pedro");
        dados.put("pedidoId", order.getId());
        dados.put("total", order.getValorTotal());
        dados.put("produtos", produtos);
        return dados;
    }

    public boolean delete (Long id){
        return dao.delete(id);
    }
    @Transactional(readOnly = true)
    public Optional<Order> findById(Long id){
        return dao.findById(id);
    }
    @Transactional(readOnly = true)
    public List<Order>findAll(){
        return dao.findAll();
    }

}
