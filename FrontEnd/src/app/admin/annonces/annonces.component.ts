import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-annonces',
    imports: [
        NgForOf
    ],
  templateUrl: './annonces.component.html',
  styleUrl: './annonces.component.css'
})
export class AnnoncesComponent {
  trajets  = [
    {depart:  'John Doe', destination: 'Paris - Lyon', capacite: '56', date: '2023-03-01' , type: "test" },

  ];

}
