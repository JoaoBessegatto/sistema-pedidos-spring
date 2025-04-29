package br.com.fatec.Produtos.dto.response;

import br.com.fatec.Produtos.entity.Produto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoResponseDTO {
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public ProdutoResponseDTO(Produto produto){
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
    }
}
