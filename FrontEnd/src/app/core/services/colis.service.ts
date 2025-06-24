import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Colis {
  id: number;
  description: string;
  poids: number;
  volume: number;
  typeColis: string;
  dimensions: string;
  dateCreation: string;
}

@Injectable({
  providedIn: 'root'
})
export class ColisService {
  private apiUrl = 'http://localhost:8080/api/colis';

  constructor(private http: HttpClient) { }

  getMyColis(): Observable<Colis[]> {
    return this.http.get<Colis[]>(`${this.apiUrl}/my`);
  }

  createColis(colisData: Partial<Colis>): Observable<Colis> {
    return this.http.post<Colis>(this.apiUrl, colisData);
  }
} 