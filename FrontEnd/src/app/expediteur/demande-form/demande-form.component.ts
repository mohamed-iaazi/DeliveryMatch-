import { Component, OnInit } from '@angular/core';
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
  demandeForm = new FormGroup({
    colis: new FormControl('', Validators.required),
    poids: new FormControl('', Validators.required),
    dimensions: new FormControl('', Validators.required)
  });

  constructor() { }

  ngOnInit(): void {
    this.expediteur = 'name';
  }

  onSubmit(): void {
    console.log(this.demandeForm.value);
    // Envoyer la demande de transport
  }
}
