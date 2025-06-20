import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Trajet {
  id: number;
  depart: string;
  destination: string;
  date: string;
  capacite: number;
  type: string;
}

@Component({
  selector: 'app-trajet-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './trajet-list.component.html',
  styleUrls: ['./trajet-list.component.css']
})
export class TrajetListComponent {
  trajets: Trajet[] = [
    { id: 1, depart: 'Paris', destination: 'Lyon', date: '2024-06-20', capacite: 3, type: 'Colis' },
    { id: 2, depart: 'Marseille', destination: 'Nice', date: '2024-06-22', capacite: 2, type: 'Document' },
    { id: 3, depart: 'Lille', destination: 'Bordeaux', date: '2024-06-25', capacite: 1, type: 'Meuble' }
  ];
} 