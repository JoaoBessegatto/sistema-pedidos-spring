package br.com.fatec.Produtos.dto.response;

import br.com.fatec.Produtos.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private List<ItemPedidoResponseDTO> itemPedidos;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.data = order.getData();
        this.valorTotal = order.getValorTotal();
        this.itemPedidos = order.getItemPedidos().stream()
                .map(ItemPedidoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
