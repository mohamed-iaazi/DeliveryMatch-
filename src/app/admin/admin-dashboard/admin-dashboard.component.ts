import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Utilisateur {
  id: number;
  nom: string;
  email: string;
  role: string;
  statut: string;
}

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  utilisateurs: Utilisateur[] = [
    { id: 1, nom: 'Alice Dupont', email: 'alice@mail.com', role: 'Expéditeur', statut: 'Actif' },
    { id: 2, nom: 'Bob Martin', email: 'bob@mail.com', role: 'Conducteur', statut: 'Suspendu' },
    { id: 3, nom: 'Charlie Durand', email: 'charlie@mail.com', role: 'Admin', statut: 'Actif' }
  ];
  stats = {
    utilisateurs: 3,
    trajets: 12,
    demandes: 8
  };
} 