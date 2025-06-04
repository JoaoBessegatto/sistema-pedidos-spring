package br.com.fatec.Produtos.controller;

import br.com.fatec.Produtos.dto.request.OrderRequestDTO;
import br.com.fatec.Produtos.dto.response.OrderResponseDTO;
import br.com.fatec.Produtos.entity.Order;
import br.com.fatec.Produtos.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
    @DeleteMapping("{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        boolean deletado = service.delete(id);
        return deletado ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    @GetMapping()
    public ResponseEntity<List<OrderResponseDTO>>findAll(){
        List<Order> pedidos = service.findAll();
        if(pedidos.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<OrderResponseDTO> dtos = pedidos.stream()
                .map(OrderResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDTO>findById(@PathVariable Long id){
        return service.findById(id)
                .map(order -> ResponseEntity.ok(new OrderResponseDTO(order)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
