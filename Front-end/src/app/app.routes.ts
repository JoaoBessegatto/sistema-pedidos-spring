import { Routes } from '@angular/router';
import { ProdutoListComponent } from './components/produto-list/produto-list.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

export const routes: Routes = [
    {path: "", redirectTo: "dashboard", pathMatch: `full`},
    {path: "produtos", component: ProdutoListComponent},
    {path: "dashboard", component: DashboardComponent}
];
