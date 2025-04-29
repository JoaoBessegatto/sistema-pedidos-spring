package br.com.fatec.Produtos.dao;

import br.com.fatec.Produtos.entity.Order;
import br.com.fatec.Produtos.entity.Produto;
import br.com.fatec.Produtos.exception.OrderNotFoundException;
import br.com.fatec.Produtos.exception.ProdutoNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class OrderDao {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Order save(Order order){
        manager.persist(order);
        return order;
    }
    @Transactional
    public boolean delete(Long id){
        Order order = this.manager.find(Order.class,id);
        if(order != null){
            manager.remove(order);
            return true;
        }else{
            throw new OrderNotFoundException("Pedido com id: " + id  + " não encontrado.");
        }
    }
    @Transactional(readOnly = true)
    public Optional<Order>findById(Long id){
        return Optional.ofNullable(manager.find(Order.class,id));
    }
    @Transactional(readOnly = true)
    public List<Order> findAll(){
        List<Order> pedidos = this.manager.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
        if (pedidos.isEmpty()) {
            throw new OrderNotFoundException("Nenhuma pedido encontrado.");
        }
        return pedidos;
    }

}
