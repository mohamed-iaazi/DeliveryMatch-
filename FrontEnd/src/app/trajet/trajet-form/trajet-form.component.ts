import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TrajetService } from '../../core/services/trajet.service';
import { Router, ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-trajet-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './trajet-form.component.html',
  styleUrls: ['./trajet-form.component.css']
})
export class TrajetFormComponent implements OnInit {
  trajetForm: FormGroup;
  isEditMode = false;
  trajetId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private trajetService: TrajetService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.trajetForm = this.fb.group({
      lieuDepart: ['', Validators.required],
      destination: ['', Validators.required],
      dateDepart: ['', Validators.required],
      poidsMax: ['', [Validators.required, Validators.min(1)]],
      longueurMax: ['', [Validators.required, Validators.min(0.1)]],
      largeurMax: ['', [Validators.required, Validators.min(0.1)]],
      hauteurMax: ['', [Validators.required, Validators.min(0.1)]],
      typeColis: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const id = params.get('id');
        if (id) {
          this.isEditMode = true;
          this.trajetId = +id;
          return this.trajetService.getTrajetById(this.trajetId);
        }
        return of(null);
      })
    ).subscribe(trajet => {
      if (trajet) {
        // We need to format the date correctly for the form input
        const dateDepart = new Date(trajet.dateDepart).toISOString().split('T')[0];
        this.trajetForm.patchValue({
          ...trajet,
          dateDepart: dateDepart,
          typeColis: trajet.typeMarchandise
        });
      }
    });
  }

  onSubmit() {
    if (this.trajetForm.valid) {
      const operation = this.isEditMode && this.trajetId
        ? this.trajetService.updateTrajet(this.trajetId, this.trajetForm.value)
        : this.trajetService.createTrajet(this.trajetForm.value);

      operation.subscribe({
        next: () => {
          this.router.navigate(['/conducteur/demandes']);
        },
        error: (err) => {
          console.error(`Failed to ${this.isEditMode ? 'update' : 'create'} trajet`, err);
        }
      });
    }
  }
} 