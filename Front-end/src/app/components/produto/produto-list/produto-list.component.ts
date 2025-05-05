import { Component, OnInit } from '@angular/core';
import { Produto } from '../../../model/produto';
import { ProdutoService } from '../../../service/produto-services';
import { CommonModule } from '@angular/common';
import { CarrinhoService } from '../../../service/carrinho.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produto-list.component.html',
  styleUrl: './produto-list.component.scss',
  standalone: true,
  imports: [CommonModule],
})
export class ProdutosComponent implements OnInit {
  produtos: Produto[] = [];

  constructor(private produtoService: ProdutoService,
      private carrinhoService: CarrinhoService
  ) {}

  ngOnInit(): void {
    this.produtoService.getAll().subscribe({
      next: (data) => {
        this.produtos = data;
      },
      error: (err) => {
        console.error('Erro ao buscar produtos:', err);
      }
    });
  }
  adicionarAoCarrinho(produto: Produto): void {
    this.carrinhoService.adicionarAoCarrinho(produto);
    alert('Produto adicionado ao carrinho!');
    console.log(this.produtos);
  }
}