package br.com.fatec.Produtos.controller;

import br.com.fatec.Produtos.dto.request.ProdutoRequestDTO;
import br.com.fatec.Produtos.dto.response.ProdutoResponseDTO;
import br.com.fatec.Produtos.entity.Produto;
import br.com.fatec.Produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO>save(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        ProdutoResponseDTO savedProduto = service.save(produtoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    @PutMapping
    public ResponseEntity<ProdutoResponseDTO>update(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        if(produtoRequestDTO.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        ProdutoResponseDTO updateProduto = service.update(produtoRequestDTO);
        return ResponseEntity.ok(updateProduto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id) {
        boolean deletado = service.delete(id);
        return deletado ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO>findById(@PathVariable Long id) {
        return service.findById(id)
                .map(produto -> ResponseEntity.ok(new ProdutoResponseDTO(produto)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        List<Produto> produtos = service.findAll();

        if(produtos.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<ProdutoResponseDTO> dtos = produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

}
