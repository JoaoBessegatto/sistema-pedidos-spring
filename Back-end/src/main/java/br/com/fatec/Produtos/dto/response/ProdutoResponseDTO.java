package br.com.fatec.Produtos.dto.response;

import br.com.fatec.Produtos.entity.Produto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String bigDescricao;
    private BigDecimal preco;

    public ProdutoResponseDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.bigDescricao = produto.getBigDescricao();
        this.preco = produto.getPreco();
    }
}
