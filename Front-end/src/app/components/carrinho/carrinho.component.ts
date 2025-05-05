import { Component, ChangeDetectorRef } from '@angular/core';
import { Produto } from '../../model/produto';
import { CarrinhoService } from '../../service/carrinho.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss'],
  standalone: true,
  imports: [CommonModule],
})
export class CarrinhoComponent {
  produtos: Produto[] = [];
  total = 0;

  constructor(private carrinhoService: CarrinhoService, private cdr: ChangeDetectorRef) {
    this.produtos = this.carrinhoService.getItens();
    this.calcularTotal();
  }

  ngOnInit(): void {
    this.produtos = this.carrinhoService.getItens();
    this.calcularTotal();
  }

  remover(index: number): void {
    this.carrinhoService.removerItem(index);
    this.produtos = this.carrinhoService.getItens();
    this.calcularTotal();
  }

  calcularTotal(): void {
    this.total = this.produtos.reduce((sum, item) => sum + item.preco, 0);
  }

  ngDoCheck(): void {
    // Forçar a detecção de mudanças após alterações no carrinho
    this.cdr.detectChanges();
  }
}
