import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Trajet, TrajetService } from '../../core/services/trajet.service';

@Component({
  selector: 'app-conducteur-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './conducteur-dashboard.component.html',
  styleUrls: ['./conducteur-dashboard.component.css']
})
export class ConducteurDashboardComponent implements OnInit {
  demandes = [
    { expediteur: 'Alice', trajet: 'Paris - Lyon', statut: 'En attente', date: new Date() },
    { expediteur: 'Bob', trajet: 'Marseille - Lille', statut: 'Acceptée', date: new Date() },
    { expediteur: 'Charlie', trajet: 'Lyon - Toulouse', statut: 'Refusée', date: new Date() }
  ];
  trajets: Trajet[] = [];

  constructor(private trajetService: TrajetService) { }

  ngOnInit(): void {
    this.loadTrajets();
  }

  loadTrajets(): void {
    this.trajetService.getMyTrajets().subscribe({
      next: (data) => {
        console.log('Fetched trajets:', data);
        this.trajets = data;
      },
      error: (err) => console.error('Failed to load trajets', err)
    });
  }

  deleteTrajet(id: number): void {
    if (confirm('Are you sure you want to delete this trajet?')) {
      this.trajetService.deleteTrajet(id).subscribe({
        next: () => this.loadTrajets(),
        error: (err) => console.error('Failed to delete trajet', err)
      });
    }
  }

  getStatusClass(statut: string): string {
    switch (statut) {
      case 'En attente':
        return 'bg-warning text-dark';
      case 'Acceptée':
        return 'bg-success';
      case 'Refusée':
        return 'bg-danger';
      default:
        return 'bg-secondary';
    }
  }
} 