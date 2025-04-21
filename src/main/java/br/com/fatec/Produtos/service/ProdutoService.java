package br.com.fatec.Produtos.service;

import br.com.fatec.Produtos.dao.ProdutoDao;
import br.com.fatec.Produtos.dto.request.ProdutoRequestDTO;
import br.com.fatec.Produtos.dto.response.ProdutoResponseDTO;
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

    public ProdutoResponseDTO save (ProdutoRequestDTO produtoRequestDTO){
        Produto produto = produtoRequestDTO.toEntity();
        Produto savedProduto = dao.save(produto);
        return new ProdutoResponseDTO(savedProduto);
    }
}
