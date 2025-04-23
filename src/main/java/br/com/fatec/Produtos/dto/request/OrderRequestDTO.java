package br.com.fatec.Produtos.dto.request;

import br.com.fatec.Produtos.entity.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {
    private Long id;

    @NotNull(message = "A data do pedido é obrigatória.")
    private LocalDateTime data;

    @NotNull(message = "Os itens do pedido são obrigatórios.")
    private List<ItemPedidoRequestDTO> itemPedidos;

    public Order toEntity() {
        Order order = new Order();
        order.setId(this.id);
        order.setData(this.data);
        // O valor total será calculado no service com base nos itens
        return order;
    }
}
