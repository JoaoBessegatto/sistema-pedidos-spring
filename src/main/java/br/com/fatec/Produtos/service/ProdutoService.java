package br.com.fatec.Produtos.service;

import br.com.fatec.Produtos.dao.ProdutoDao;
import br.com.fatec.Produtos.dto.request.ProdutoRequestDTO;
import br.com.fatec.Produtos.dto.response.ProdutoResponseDTO;
import br.com.fatec.Produtos.entity.Produto;
import br.com.fatec.Produtos.exception.ProdutoNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ProdutoResponseDTO update (ProdutoRequestDTO produtoRequestDTO){
        Produto produtoExistente = dao.findById(produtoRequestDTO.getId())
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
        if(produtoRequestDTO.getNome() != null){
            produtoExistente.setNome(produtoRequestDTO.getNome());
        }
        if(produtoRequestDTO.getPreco() != null){
            produtoExistente.setPreco(produtoRequestDTO.getPreco());
        }
        Produto updateProduto = dao.update(produtoExistente);
        return new ProdutoResponseDTO(updateProduto);
    }

    public boolean delete (Long id){
        return dao.delete(id);
    }

    @Transactional(readOnly = true)
    public Optional<Produto> findById(Long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Produto>findAll(){
        return dao.findAll();
    }

}



