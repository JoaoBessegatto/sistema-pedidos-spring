import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produto } from '../model/produto';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  private apiUrl = 'http://localhost:8080/produtos';

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<Produto[]> {
    return this.httpClient.get<Produto[]>(this.apiUrl);
  }

  getById(id: number): Observable<Produto> {
    return this.httpClient.get<Produto>(`${this.apiUrl}/${id}`);
  }
}
