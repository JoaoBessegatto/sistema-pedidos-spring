import { Component } from '@angular/core';
import { Produto } from '../../models/produto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-produto-list',
  imports: [CommonModule],
  templateUrl: './produto-list.component.html',
  styleUrl: './produto-list.component.scss'
})
export class ProdutoListComponent {
  lista: Produto[] = [];

  constructor() {
    let produto1: Produto = new Produto();
    produto1.nome = `mouse`;
    produto1.descricao = `mouse gamer`;
    produto1.preco = 250.00;
  
    let produto2: Produto = new Produto();
    produto2.nome = `monitor`;
    produto2.descricao = `monitor gamer`;
    produto2.preco = 750.00;
  
    let produto3: Produto = new Produto();
    produto3.nome = `computador`;
    produto3.descricao = `computador gamer`;
    produto3.preco = 3000.00;
  
    this.lista.push(produto1, produto2, produto3);
  }
  
}
