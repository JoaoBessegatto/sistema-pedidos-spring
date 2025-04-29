package br.com.fatec.Produtos.dao;

import br.com.fatec.Produtos.entity.Produto;
import br.com.fatec.Produtos.exception.InvalidProdutoException;
import br.com.fatec.Produtos.exception.ProdutoNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProdutoDao {
    @PersistenceContext
    EntityManager manager;
    @Transactional
    public boolean existByNome(String nome){
        return manager.createQuery(
                        "SELECT COUNT(p) > 0 FROM Produto p WHERE p.nome = :nome", Boolean.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public Produto save(Produto produto){
        if(existByNome(produto.getNome())){
            throw new InvalidProdutoException("Já existe um produto cadastrado com esse nome");
        }
        manager.persist(produto);
        return produto;
    }
    @Transactional
    public Produto update(Produto produto){
        Produto produtoExistente = manager.find(Produto.class,produto.getId());
        if (produtoExistente == null){
            throw new ProdutoNotFoundException("Produto não encontrado");
        }
        return manager.merge(produtoExistente);
    }
    @Transactional
    public boolean delete(Long id){
        Produto produto = this.manager.find(Produto.class, id);
        if(produto != null){
            this.manager.remove(produto);
            return true;
        }else{
            throw new ProdutoNotFoundException("Produto com id " + id + " não encontrado.");
        }
    }
    @Transactional(readOnly = true)
    public Optional<Produto>findById(Long id) {
        return Optional.ofNullable(manager.find(Produto.class, id));
    }

    @Transactional(readOnly = true)
    public List<Produto> findAll() {
        List<Produto> produtos = this.manager.createQuery("SELECT p FROM Produto p", Produto.class)
                .getResultList();

        if (produtos.isEmpty()) {
            throw new ProdutoNotFoundException("Nenhum Produto encontrado.");
        }
        return produtos;
    }

}
