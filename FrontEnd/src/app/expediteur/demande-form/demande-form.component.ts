import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-demande-form',
  templateUrl: './demande-form.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./demande-form.component.css']
})
export class DemandeFormComponent implements OnInit {
  expediteur: string= "";
  @Output() colisCreated = new EventEmitter<any>();
  demandeForm = new FormGroup({
    description: new FormControl('', Validators.required),
    poids: new FormControl('', Validators.required),
    dimensions: new FormControl('', Validators.required),
    typeColis: new FormControl('', Validators.required)
  });

  constructor() { }

  ngOnInit(): void {
    this.expediteur = 'name';
  }

  onSubmit(): void {
    if (this.demandeForm.valid) {
      this.colisCreated.emit(this.demandeForm.value);
    }
  }
}
