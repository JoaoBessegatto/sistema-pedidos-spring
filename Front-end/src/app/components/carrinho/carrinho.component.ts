import { Component, ChangeDetectorRef } from '@angular/core';
import { Produto } from '../../model/produto';
import { CarrinhoService } from '../../service/carrinho.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

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

  constructor(
    private carrinhoService: CarrinhoService,
    private cdr: ChangeDetectorRef,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.produtos = this.carrinhoService.getItens();
    this.calcularTotal();
  }

  remover(index: number): void {
    this.carrinhoService.removerItem(index);
    this.produtos = this.carrinhoService.getItens();
    this.calcularTotal();
  }

  calcularTotal(): number {
    this.total = this.produtos.reduce((soma: number, item: Produto) => soma + item.preco, 0);
    return this.total;
  }

  ngDoCheck(): void {
    this.cdr.detectChanges();
  }

  finalizarCompra(): void {
    const itemPedidosMap = new Map<number, number>();

    this.produtos.forEach(produto => {
      const id = produto.id;
      if (itemPedidosMap.has(id)) {
        itemPedidosMap.set(id, itemPedidosMap.get(id)! + 1);
      } else {
        itemPedidosMap.set(id, 1);
      }
    });

    const itemPedidos = Array.from(itemPedidosMap.entries()).map(([produtoId, quantidade]) => ({
      produtoId,
      quantidade
    }));

    const pedido = { itemPedidos };

    this.http.post('http://localhost:8080/pedido', pedido).subscribe({
      next: () => {
        alert('Compra finalizada com sucesso!');
        this.carrinhoService.limparCarrinho();
        this.produtos = [];
        this.total = 0;
        this.router.navigate(['/admin/produtos']);
      },
      error: (err) => {
        console.error('Erro ao finalizar compra:', err);
        alert('Erro ao finalizar compra.');
      }
    });
  }
}
