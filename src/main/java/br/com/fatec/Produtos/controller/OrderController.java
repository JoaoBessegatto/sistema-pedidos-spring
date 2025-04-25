package br.com.fatec.Produtos.controller;

import br.com.fatec.Produtos.dto.request.OrderRequestDTO;
import br.com.fatec.Produtos.dto.response.OrderResponseDTO;
import br.com.fatec.Produtos.dto.response.ProdutoResponseDTO;
import br.com.fatec.Produtos.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedido")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO>save(@RequestBody @Valid OrderRequestDTO orderRequestDTO){
        OrderResponseDTO OrderSave = service.save(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderSave);
    }
}
