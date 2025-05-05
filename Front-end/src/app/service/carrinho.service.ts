import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {
  private itens: Produto[] = [];
  private cartCountSubject = new BehaviorSubject<number>(0);
  cartCount$ = this.cartCountSubject.asObservable();

  constructor() {
    this.itens = this.carregarDoStorage();
    this.atualizarContagem();
  }

  adicionarAoCarrinho(produto: Produto): void {
    this.itens.push(produto);
    this.salvarNoStorage();
    this.atualizarContagem();
  }

  removerItem(index: number): void {
    if (index >= 0 && index < this.itens.length) {
      this.itens.splice(index, 1);
      this.salvarNoStorage();
      this.atualizarContagem();
    }
  }

  limparCarrinho(): void {
    this.itens = [];
    this.salvarNoStorage();
    this.atualizarContagem();
  }

  getItens(): Produto[] {
    return [...this.itens]; // retorna cópia para evitar manipulação externa
  }

  private salvarNoStorage(): void {
    localStorage.setItem('carrinho', JSON.stringify(this.itens));
  }

  private carregarDoStorage(): Produto[] {
    const dados = localStorage.getItem('carrinho');
    try {
      return dados ? JSON.parse(dados) : [];
    } catch {
      return [];
    }
  }

  private atualizarContagem(): void {
    const total = this.itens.reduce((acc, item) => acc + (item.quantidade ?? 1), 0);
    this.cartCountSubject.next(total);
  }
}
