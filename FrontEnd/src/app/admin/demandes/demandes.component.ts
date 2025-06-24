import { Component, OnInit } from '@angular/core';
import { NgForOf } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-demandes',
  imports: [NgForOf],
  templateUrl: './demandes.component.html',
  styleUrl: './demandes.component.css'
})
export class DemandesComponent implements OnInit {
  demandes: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/demandes/all').subscribe(data => {
      this.demandes = data;
    });
  }
}
