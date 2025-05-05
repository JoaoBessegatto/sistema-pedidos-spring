import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { Produto } from '../../model/produto';
import { CarrinhoService } from '../../service/carrinho.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss'],
  standalone: true,
  imports: [CommonModule],
})
export class CarrinhoComponent implements OnInit {
  produtos: Produto[] = [];
  total = 0;
  cartCount$: Observable<number>;

  constructor(
    private carrinhoService: CarrinhoService,
    private cdr: ChangeDetectorRef,
    private http: HttpClient,
    private router: Router
  ) {
    this.cartCount$ = this.carrinhoService.cartCount$;
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

  calcularTotal(): number {
    this.total = this.produtos.reduce((soma: number, item: Produto) => soma + item.preco * (item.quantidade || 1), 0);
    return this.total;
  }

  ngDoCheck(): void {
    this.cdr.detectChanges();
  }

  // Função para garantir que a quantidade não seja nula ou negativa
  verificarQuantidade(produto: Produto): number {
    return produto.quantidade > 0 ? produto.quantidade : 1;  // Garantir que a quantidade seja pelo menos 1
  }

  finalizarCompra(): void {
    // Mapeando os itens para enviar para a API, garantindo que a quantidade não seja nula ou negativa
    const itemPedidosMap = new Map<number, number>();

    this.produtos.forEach(produto => {
      const id = produto.id;
      const quantidade = this.verificarQuantidade(produto);  // Garantir que a quantidade seja válida
      if (itemPedidosMap.has(id)) {
        itemPedidosMap.set(id, itemPedidosMap.get(id)! + quantidade);  // Soma a quantidade de um produto já existente
      } else {
        itemPedidosMap.set(id, quantidade);  // Adiciona um novo produto
      }
    });

    // Mapeia os itens para o formato esperado pela API
    const itemPedidos = Array.from(itemPedidosMap.entries()).map(([produtoId, quantidade]) => ({
      produtoId,
      quantidade
    }));

    // Corpo da requisição para finalizar a compra
    const pedido = { itemPedidos };

    // Envia a requisição para o backend
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
        alert('Erro ao finalizar compra. Por favor, tente novamente.');
      }
    });
  }
}
