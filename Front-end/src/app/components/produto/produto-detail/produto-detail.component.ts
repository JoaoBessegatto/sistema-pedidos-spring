import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CarrinhoService } from '../../../service/carrinho.service'; // Importe o RouterModule
import { Produto } from '../../../model/produto';
import { CommonModule } from '@angular/common'; // Importe o CommonModule para usar o pipe currency
import { ProdutoService } from '../../../service/produto-services';


@Component({
  selector: 'app-produto-detail',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './produto-detail.component.html',
  styleUrls: ['./produto-detail.component.scss'],
})
export class ProdutoDetailComponent implements OnInit {
  produto: Produto | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private produtoService: ProdutoService,   // ✅ Usar o serviço para buscar o produto
    private carrinhoService: CarrinhoService
  ) {}

  ngOnInit(): void {
    const produtoId = this.route.snapshot.queryParamMap.get('id');
    if (produtoId) {
      this.produtoService.getById(parseInt(produtoId)).subscribe({
        next: (produto) => {
          this.produto = produto;
        },
        error: (err) => {
          console.error('Erro ao carregar o produto:', err);
          alert('Produto não encontrado.');
          this.router.navigate(['/admin/produtos']);
        }
      });
    } else {
      alert('Produto não encontrado.');
      this.router.navigate(['/admin/produtos']);
    }
  }

  adicionarAoCarrinho(): void {
    if (this.produto) {
      this.carrinhoService.adicionarAoCarrinho(this.produto);
      alert('Produto adicionado ao carrinho!');
    }
  }
}