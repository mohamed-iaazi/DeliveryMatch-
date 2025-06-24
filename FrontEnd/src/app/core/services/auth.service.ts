import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';


export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
  role?: string;
}

export interface AuthResponse {
  token: string;
  username: string;
  role: string;
}
@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = 'http://localhost:8080/api/auth';
  private currentUserSubject = new BehaviorSubject<AuthResponse | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

    constructor(
      private http: HttpClient,
      private router: Router
  ) {
      // Check if user info exists in localStorage
      const storedUser = localStorage.getItem('currentUser');
      if (storedUser) {
        this.currentUserSubject.next(JSON.parse(storedUser));
      }
    }

  login(request: { password: any; login: any; }): Observable<AuthResponse> {
      return this.http.post<AuthResponse>(`${this.apiUrl}/login`, request)
        .pipe(
          tap(response => {
            localStorage.setItem('currentUser', JSON.stringify(response));
            this.currentUserSubject.next(response);
          })
        );
    }

    register(request: { password: any; prenom: any; nom: any; login: any; role?: string }): Observable<AuthResponse> {
      const registerData = {
        login: request.login,
        password: request.password,
        nom: request.nom,
        prenom: request.prenom
      };

      return this.http.post<AuthResponse>(`${this.apiUrl}/register`, registerData)
        .pipe(
          tap(response => {
            localStorage.setItem('currentUser', JSON.stringify(response));
            this.currentUserSubject.next(response);
          })
        );
    }

    registerDriver(request: { password: any; prenom: any; nom: any; login: any; role?: string }): Observable<AuthResponse> {
      const registerData = {
        login: request.login,
        password: request.password,
        nom: request.nom,
        prenom: request.prenom
      };

      return this.http.post<AuthResponse>(`${this.apiUrl}/register-driver`, registerData)
        .pipe(
          tap(response => {
            localStorage.setItem('currentUser', JSON.stringify(response));
            this.currentUserSubject.next(response);
          })
        );
    }

    logout(): void {
      localStorage.removeItem('currentUser');
      this.currentUserSubject.next(null);
      this.router.navigate(['/login']);
    }

    isLoggedIn(): boolean {
      return !!this.currentUserSubject.value;
    }

    getCurrentUser(): AuthResponse | null {
      return this.currentUserSubject.value;
    }

    hasRole(role: string): boolean {
      const user = this.getCurrentUser();
      return user?.role === role;
    }

    isAdmin(): boolean {
      return this.hasRole('ADMINISTRATEUR');
    }

    getToken(): string | null {
      const token = this.currentUserSubject.value?.token;
      return token ? `Bearer ${token}` : null;
    }

  }

