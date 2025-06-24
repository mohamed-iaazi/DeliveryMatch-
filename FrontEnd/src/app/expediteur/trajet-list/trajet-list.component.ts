import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CommonModule, NgForOf} from '@angular/common';
import { Trajet, TrajetService } from '../../core/services/trajet.service';

@Component({
  selector: 'app-trajet-list',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    CommonModule
  ],
  templateUrl: './trajet-list.component.html',
  styleUrl: './trajet-list.component.css'
})
export class TrajetListComponent implements OnInit {
  @Output() trajetSelected = new EventEmitter<number>();
  trajets: Trajet[] = [];
  filteredTrajets: Trajet[] = [];
  searchTerm = '';

  constructor(private trajetService: TrajetService) { }

  ngOnInit(): void {
    this.trajetService.getAllTrajets().subscribe({
      next: (data: Trajet[]) => {
        this.trajets = data;
        this.filteredTrajets = data;
      },
      error: (err: any) => {
        console.error('Failed to load trajets', err);
      }
    });
  }

  searchTrajets(): void {
    this.filteredTrajets = this.trajets.filter(trajet => {
      return trajet.lieuDepart.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        trajet.destination.toLowerCase().includes(this.searchTerm.toLowerCase());
    });
  }

  selectTrajet(id: number): void {
    this.trajetSelected.emit(id);
  }
}
