import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Trajet, TrajetService } from '../../core/services/trajet.service';

@Component({
  selector: 'app-trajet-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './trajet-detail.component.html',
  styleUrls: ['./trajet-detail.component.css']
})
export class TrajetDetailComponent implements OnChanges {
  @Input() trajetId!: number;
  trajet: Trajet | null = null;

  constructor(private trajetService: TrajetService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['trajetId'] && this.trajetId) {
      this.trajetService.getTrajetById(this.trajetId).subscribe({
        next: (data) => this.trajet = data,
        error: (err) => console.error('Failed to load trajet details', err)
      });
    }
  }
}
