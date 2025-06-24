import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./auth/auth.component').then(m => m.AuthComponent),
  },
  {
    path: 'admin/dashboard',
    loadComponent: () => import('./admin/admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent),
  },
  {
    path: 'conducteur/demandes',
    loadComponent: () => import('./conducteur/demande-list/conducteur-dashboard.component').then(m => m.ConducteurDashboardComponent),
  },
  {
    path: 'expediteur/trajets',
    loadComponent: () => import('./expediteur/trajet-list/trajet-list.component').then(m => m.TrajetListComponent),
  },
  {
    path: 'trajet/new',
    loadComponent: () => import('./trajet/trajet-form/trajet-form.component').then(m => m.TrajetFormComponent),
  },
  {
    path: 'trajet/edit/:id',
    loadComponent: () => import('./trajet/trajet-form/trajet-form.component').then(m => m.TrajetFormComponent),
  }
];
