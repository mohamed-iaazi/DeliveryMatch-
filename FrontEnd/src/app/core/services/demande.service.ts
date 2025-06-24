import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Demande } from '../../models/demande.model';

@Injectable({
  providedIn: 'root'
})
export class DemandeService {
  private apiUrl = 'http://localhost:8080/api/demandes';

  constructor(private http: HttpClient) { }

  getMyDemandes(): Observable<Demande[]> {
    return this.http.get<Demande[]>(`${this.apiUrl}/my`);
  }

  cancelDemande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  updateDemande(id: number, data: Partial<Demande>): Observable<Demande> {
    return this.http.put<Demande>(`${this.apiUrl}/${id}`, data);
  }

  reserveTrajet(trajetId: number, colisId: number): Observable<Demande> {
    return this.http.post<Demande>(`${this.apiUrl}?trajetId=${trajetId}&colisId=${colisId}`, {});
  }
} 