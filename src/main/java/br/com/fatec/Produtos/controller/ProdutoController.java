package br.com.fatec.Produtos.controller;

import br.com.fatec.Produtos.dto.request.ProdutoRequestDTO;
import br.com.fatec.Produtos.dto.response.ProdutoResponseDTO;
import br.com.fatec.Produtos.entity.Produto;
import br.com.fatec.Produtos.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO>save(@RequestBody @Valid ProdutoRequestDTO autorRequestDTO) {

    }


}
