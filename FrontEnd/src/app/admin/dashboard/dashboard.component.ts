import { Component } from '@angular/core';
import {DemandesComponent} from '../demandes/demandes.component';
import {NgClass, NgSwitch, NgSwitchCase} from '@angular/common';
import {AnnoncesComponent} from '../annonces/annonces.component';
import {UtilisateursComponent} from '../utilisateurs/utilisateurs.component';
import {ApercuComponent} from '../apercu/apercu.component';

@Component({
  selector: 'app-dashboard',
  imports: [
    DemandesComponent,
    NgSwitchCase,
    AnnoncesComponent,
    UtilisateursComponent,
    NgSwitch,
    ApercuComponent,
    NgClass
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  selectedComponent = 'Aperçu';

  selectComponent(component: string) {
    this.selectedComponent = component;
  }
}
