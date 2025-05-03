import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Produto {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  private apiUrl = 'http://localhost:8080/produto';
  constructor(private http: HttpClient){}

  getProdutos(): Observable<Produto[]>{
    return this.http.get<Produto[]>(this.apiUrl);
  }
}
