package br.com.fatec.Produtos.dao;

import br.com.fatec.Produtos.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
