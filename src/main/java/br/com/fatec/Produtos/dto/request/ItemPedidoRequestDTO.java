package br.com.fatec.Produtos.dto.request;

import br.com.fatec.Produtos.entity.ItemPedido;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoRequestDTO {
    @NotNull(message = "O ID do produto é obrigatório.")
    private Long produtoId;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
    private Integer quantidade;

    public ItemPedido toEntity() {
        ItemPedido item = new ItemPedido();
        item.setQuantidade(this.quantidade);
        return item;
    }
}
