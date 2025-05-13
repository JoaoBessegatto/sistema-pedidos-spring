import { Routes } from '@angular/router';
import { LoginComponent } from './components/layout/login/login.component';
import { PrincipalComponent } from './components/layout/principal/principal.component';
import { ProdutosComponent } from './components/produto/produto-list/produto-list.component';
import { ProdutoDetailComponent } from './components/produto/produto-detail/produto-detail.component';
import { CarrinhoComponent } from './components/carrinho/carrinho.component';

export const routes: Routes = [   
  { path: "", redirectTo: "login", pathMatch: 'full' },
  { path: "login", component: LoginComponent },
  {
    path: "admin", component: PrincipalComponent, children: [
      { path: "produtos", component: ProdutosComponent },
      { path: "produtos/detalhes", component: ProdutoDetailComponent },
      { path: "carrinho", component: CarrinhoComponent }
    ]
  }
];
