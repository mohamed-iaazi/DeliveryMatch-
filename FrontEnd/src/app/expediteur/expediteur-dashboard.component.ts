import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TrajetListComponent } from './trajet-list/trajet-list.component';
import { TrajetDetailComponent } from './trajet-detail/trajet-detail.component';
import { DemandeService } from '../core/services/demande.service';
import { Demande } from '../models/demande.model';
import { ColisService, Colis } from '../core/services/colis.service';
import { DemandeFormComponent } from './demande-form/demande-form.component';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-expediteur-dashboard',
  standalone: true,
  imports: [CommonModule, TrajetListComponent, TrajetDetailComponent, CommonModule, DemandeFormComponent, FormsModule],
  templateUrl: './expediteur-dashboard.component.html',
  styleUrls: ['./expediteur-dashboard.component.css']
})
export class ExpediteurDashboardComponent implements OnInit {
  selectedTrajetId: number | null = null;
  demandes: Demande[] = [];
  successMessage: string = '';
  errorMessage: string = '';
  colisList: Colis[] = [];
  selectedColisId: number | null = null;
  showColisForm: boolean = false;
  showColisFormForAdd: boolean = false;

  constructor(private demandeService: DemandeService, private colisService: ColisService) {}

  ngOnInit() {
    this.loadDemandes();
    this.loadColis();
  }

  loadDemandes() {
    this.demandeService.getMyDemandes().subscribe((data) => {
      this.demandes = data.map((demande: any) => ({
        id: demande.id,
        trajet: demande.trajet ? `${demande.trajet.lieuDepart} → ${demande.trajet.destination}` : '',
        statut: demande.statut,
        date: demande.trajet && demande.trajet.dateDepart ? demande.trajet.dateDepart : '',
        expediteur: '', // Not used in table, but required by interface
      }));
    });
  }

  loadColis() {
    this.colisService.getMyColis().subscribe((data) => {
      this.colisList = data;
      if (data.length > 0) {
        this.selectedColisId = data[0].id;
      }
    });
  }

  onTrajetSelected(id: number) {
    this.selectedTrajetId = id;
  }

  clearSelection() {
    this.selectedTrajetId = null;
  }

  cancelDemande(id: number) {
    this.demandeService.cancelDemande(id).subscribe({
      next: () => {
        this.successMessage = 'Demande annulée avec succès!';
        this.errorMessage = '';
        this.loadDemandes();
        setTimeout(() => { this.successMessage = ''; }, 3000);
      },
      error: () => {
        this.errorMessage = 'Erreur lors de l\'annulation de la demande.';
        this.successMessage = '';
      }
    });
  }

  modifyDemande(id: number) {
    // Logic to open a modal or navigate to a form for modifying the demande
    // This can be implemented as needed
  }

  reserveTrajet() {
    if (!this.selectedTrajetId || !this.selectedColisId) return;
    this.demandeService.reserveTrajet(this.selectedTrajetId, this.selectedColisId).subscribe({
      next: () => {
        this.successMessage = 'Trajet réservé avec succès!';
        this.errorMessage = '';
        this.loadDemandes();
      },
      error: () => {
        this.errorMessage = 'Erreur lors de la réservation.';
        this.successMessage = '';
      }
    });
  }

  onReserveClick() {
    this.showColisForm = true;
  }

  onAddNewColisClick() {
    this.showColisFormForAdd = true;
  }

  handleColisCreated(colisData: any) {
    this.colisService.createColis(colisData).subscribe({
      next: (colis) => {
        this.colisList.push(colis);
        this.selectedColisId = colis.id;
        this.showColisFormForAdd = false;
        this.successMessage = 'Colis créé avec succès!';
        this.errorMessage = '';
      },
      error: () => {
        this.errorMessage = 'Erreur lors de la création du colis.';
        this.successMessage = '';
      }
    });
  }
}
