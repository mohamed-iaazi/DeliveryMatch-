import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: 'register', loadComponent: () => import('./auth/register/register.component').then(m => m.RegisterComponent) },
  { path: 'login', loadComponent: () => import('./auth/login/login.component').then(m => m.LoginComponent) },
  { path: 'profile', loadComponent: () => import('./user/profile/profile.component').then(m => m.ProfileComponent) },
  { path: 'trajets', loadComponent: () => import('./trajet/trajet-list/trajet-list.component').then(m => m.TrajetListComponent) },
  { path: 'demandes', loadComponent: () => import('./demande/demande-list/demande-list.component').then(m => m.DemandeListComponent) },
  { path: 'admin', loadComponent: () => import('./admin/admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent) },
  { path: '', redirectTo: '/register', pathMatch: 'full' },
  { path: '**', redirectTo: '/register' }
];
