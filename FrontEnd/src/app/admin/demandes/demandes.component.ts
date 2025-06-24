import { Component } from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-demandes',
  imports: [
    NgForOf
  ],
  templateUrl: './demandes.component.html',
  styleUrl: './demandes.component.css'
})
export class DemandesComponent {
  demandes = [
    { expediteur: 'John Doe', trajet: 'Paris - Lyon', statut: 'En cours', date: '2023-03-01' },
    { expediteur: 'Jane Doe', trajet: 'Lyon - Paris', statut: 'Terminé', date: '2023-03-02' },
    { expediteur: 'Bob Smith', trajet: 'Paris - Marseille', statut: 'En attente', date: '2023-03-03' },
    // Add more demandes here...
  ];
}
