import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { Router} from '@angular/router';
import {AuthService} from '../core/services/auth.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-auth',
  standalone: true,
  templateUrl: './auth.component.html',
  imports: [
    FormsModule,
    CommonModule
  ],
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  returnUrl: string = '/';
  error: string = '';
  success: string = '';
  isRegistering = false;
  constructor(
    private authService: AuthService,
    private router: Router,

) {}

  loginData = {
    login: '',
    password: ''
  };

  registerData = {
    prenom: '',
    nom: '',
    login: '',
    password: '',
    role: ''
  };

  toggle(register: boolean) {
    this.isRegistering = register;
  }

  onLogin() {
    console.log('Login:', this.loginData);
    // TODO: Call login API
    this.error = '';
    this.authService.login(this.loginData)
      .subscribe({
        next: (response) => {
          this.redirectByRole(response.role);
        },
        error: (error) => {
          this.error = error.error?.error || 'Invalid username or password';
        }
      });
  }


  onRegister() {
    this.error = '';
    this.success = '';
    const registrationObservable = this.registerData.role === 'CONDUCTEUR'
      ? this.authService.registerDriver(this.registerData)
      : this.authService.register(this.registerData);

    registrationObservable.subscribe({
      next: (response) => {
        this.success = 'Registration successful! Redirecting...';
        setTimeout(() => {
          this.redirectByRole(response.role);
        }, 2000);
      },
      error: (error) => {
        this.error = error.error?.error || 'Registration failed';
      }
    });
  }

  private redirectByRole(role: string) {
    switch (role) {
      case 'ADMINISTRATEUR':
        this.router.navigate(['/admin/dashboard']);
        break;
      case 'CONDUCTEUR':
        this.router.navigate(['/conducteur/demandes']);
        break;
      case 'UTILISATEUR':
        this.router.navigate(['/expediteur/trajets']);
        break;
      default:
        this.router.navigate(['/']);
        break;
    }
  }
}
