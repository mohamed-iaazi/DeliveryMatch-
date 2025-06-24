import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-demande',
    imports: [
        NgForOf
    ],
  templateUrl: './demande.component.html',
  styleUrl: './demande.component.css'
})
export class DemandeComponent {
  demandes: any;

}
