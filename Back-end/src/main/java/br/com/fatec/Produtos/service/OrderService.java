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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public OrderResponseDTO save (OrderRequestDTO orderRequestDTO){
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
        emailService.enviarEmailTexto("bfjoaop@gmail.com","Pedido concluido com sucesso", "o pedido tem ");
        return new OrderResponseDTO(orderSaved);
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
