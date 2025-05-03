import { Component, OnInit } from '@angular/core';
import { Produto } from '../../../model/produto';
import { ProdutoService } from '../../../service/produto-services';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-produtos',
  templateUrl: './produto-list.component.html',
  standalone: true,
  imports: [CommonModule],
})
export class ProdutosComponent implements OnInit {
  produtos: Produto[] = [];

  constructor(private produtoService: ProdutoService) {}

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
}