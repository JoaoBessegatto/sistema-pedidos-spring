import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CarrinhoService } from '../../../service/carrinho.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'] 
})
export class MenuComponent {
  isCollapsed = true;

  cartCount$ = this.carrinhoService.cartCount$;

  constructor(private carrinhoService: CarrinhoService) {}
}
