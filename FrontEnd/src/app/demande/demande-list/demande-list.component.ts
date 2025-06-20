import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Demande {
  id: number;
  expediteur: string;
  trajet: string;
  statut: string;
  date: string;
}

@Component({
  selector: 'app-demande-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './demande-list.component.html',
  styleUrls: ['./demande-list.component.css']
})
export class DemandeListComponent {
  demandes: Demande[] = [
    { id: 1, expediteur: 'Alice', trajet: 'Paris → Lyon', statut: 'En attente', date: '2024-06-20' },
    { id: 2, expediteur: 'Bob', trajet: 'Marseille → Nice', statut: 'Acceptée', date: '2024-06-22' },
    { id: 3, expediteur: 'Charlie', trajet: 'Lille → Bordeaux', statut: 'Refusée', date: '2024-06-25' }
  ];
} 