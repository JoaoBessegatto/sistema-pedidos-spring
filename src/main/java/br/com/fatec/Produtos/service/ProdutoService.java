package br.com.fatec.Produtos.service;

import br.com.fatec.Produtos.dao.ProdutoDao;
import br.com.fatec.Produtos.entity.Produto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProdutoService {
    private final ProdutoDao dao;

    public ProdutoService(ProdutoDao dao){
        this.dao = dao;
    }

    public Produto save (Produto Produto){
        Produto productSave = dao.save(Produto);
        return productSave;
    }
}
