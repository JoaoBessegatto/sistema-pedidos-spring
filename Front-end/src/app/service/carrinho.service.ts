import { Injectable } from '@angular/core';
import { Produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {
  private readonly STORAGE_KEY = 'carrinho';
  private itens: Produto[] = [];

  constructor() {
    this.itens = this.carregarDoStorage();
  }

  adicionarAoCarrinho(produto: Produto) {
    this.itens.push({ ...produto });
    this.salvarNoStorage();
  }

  getItens(): Produto[] {
    return this.itens;
  }

  limparCarrinho() {
    this.itens = [];
    this.salvarNoStorage();
  }

  removerItem(index: number) {
    this.itens.splice(index, 1);
    this.salvarNoStorage();
  }

  private salvarNoStorage() {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.itens));
  }

  private carregarDoStorage(): Produto[] {
    const dados = localStorage.getItem(this.STORAGE_KEY);
    return dados ? JSON.parse(dados) : [];
  }
}
