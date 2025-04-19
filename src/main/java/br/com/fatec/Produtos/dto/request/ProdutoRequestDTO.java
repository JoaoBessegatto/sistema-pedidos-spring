package br.com.fatec.Produtos.dto.request;

import br.com.fatec.Produtos.entity.Produto;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProdutoRequestDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório e não pode estar vazio.")
    @Size(max = 45, message = "O nome deve ter no máximo 45 caracteres.")
    private String nome;

    @Size(min = 1, message = "A descrição não pode ser vazia.")
    private String descricao;

    @NotBlank(message = "O preço é obrigatório e não pode ser vazio.")
    @NegativeOrZero(message = "O preço não pode ser negativo ou zero.")
    private BigDecimal preco;

    @NotBlank(message = "A quantidade é obrigatória e não pode ser vazia.")
    @NegativeOrZero(message = "A quantidade não pode ser negativa ou zero.")
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
