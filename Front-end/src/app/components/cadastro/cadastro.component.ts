import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Usuario } from '../../auth/usuario';
import { UsuarioService } from '../../auth/usuario.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [MdbFormsModule, FormsModule,CommonModule,RouterModule],
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent {
  usuario: Usuario = new Usuario();
  isLoading: boolean = false;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  cadastrar(): void {
    this.isLoading = true;
    this.successMessage = '';
    this.errorMessage = '';

    this.usuarioService.cadastrar(this.usuario).subscribe({
      next: () => {
        this.isLoading = false;
        this.successMessage = 'Usuário cadastrado com sucesso!';
        setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: err => {
        this.isLoading = false;
        this.errorMessage = 'Erro ao cadastrar usuário. Verifique os dados.';
        console.error(err);
      }
    });
  }
}
