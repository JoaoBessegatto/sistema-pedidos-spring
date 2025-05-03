import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  private apiUrl = 'http://localhost:8080/produtos'; 
  constructor(private http: HttpClient) {}

  getAll(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.apiUrl);
  }
}
