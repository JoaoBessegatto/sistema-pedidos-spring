import { Component } from '@angular/core';
import { ProdutoListComponent } from "../produto-list/produto-list.component";

@Component({
  selector: 'app-dashboard',
  imports: [ProdutoListComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

}
