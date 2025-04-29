package br.com.fatec.Produtos.dto.request;

import br.com.fatec.Produtos.entity.Produto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoRequestDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório e não pode estar vazio.")
    @Size(max = 45, message = "O nome deve ter no máximo 45 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória e não pode estar vazia.")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    @Min(value = 1, message = "O preço deve ser maior que zero.")
    private BigDecimal preco;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
    private Integer quantidadeEstoque;

    public Produto toEntity(){
        Produto produto = new Produto();
        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
        produto.setQuantidadeEstoque(this.quantidadeEstoque);
        return produto;
    }
}
