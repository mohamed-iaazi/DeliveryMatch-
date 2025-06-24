import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trajet-detail',
  templateUrl: './trajet-detail.component.html',
  styleUrls: ['./trajet-detail.component.css']
})
export class TrajetDetailComponent implements OnInit {
  trajet = {
    depart: 'Paris',
    arrivee: 'Lyon',
    date: '2023-03-01',
    heure: '08:00',
    distance: 465,
    duree: 5,
    conducteur: {
      nom: 'John Doe',
      note: 4.5
    }
  };

  constructor() { }

  ngOnInit(): void {
  }
}
