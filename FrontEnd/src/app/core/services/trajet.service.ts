import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Trajet {
    id: number;
    lieuDepart: string;
    destination: string;
    etapesIntermediaires: string[];
    dateDepart: string;
    poidsMax: number;
    volumeMax: number;
    typeMarchandise: string;
    capaciteDisponible: number;
}


@Injectable({
  providedIn: 'root'
})
export class TrajetService {

  private apiUrl = 'http://localhost:8080/api/trajets';

  constructor(private http: HttpClient) { }

  getAllTrajets(): Observable<Trajet[]> {
    return this.http.get<Trajet[]>(this.apiUrl);
  }

  getMyTrajets(): Observable<Trajet[]> {
    return this.http.get<Trajet[]>(`${this.apiUrl}/my-trajets`);
  }

  getTrajetById(id: number): Observable<Trajet> {
    return this.http.get<Trajet>(`${this.apiUrl}/${id}`);
  }

  createTrajet(trajet: any): Observable<Trajet> {
    return this.http.post<Trajet>(this.apiUrl, trajet);
  }

  updateTrajet(id: number, trajet: any): Observable<Trajet> {
    return this.http.put<Trajet>(`${this.apiUrl}/${id}`, trajet);
  }

  deleteTrajet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
} 