package br.com.fatec.Produtos.dao;

import br.com.fatec.Produtos.entity.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ProdutoDao {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Produto save(Produto produto){
        manager.persist(produto);
        return produto;
    }
}
