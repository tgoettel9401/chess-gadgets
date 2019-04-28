import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Tournament} from "../models/Tournament";
import {catchError, tap} from "rxjs/operators";
import {Player} from "../models/Player";

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/api/';

  deleteAllTournaments(): Observable<any> {
    let url = this.baseUrl + 'tournaments/deleteAllTournaments';
    return this.http.get(url);
  }

  postMultipleTournaments(tournaments: Tournament[]): Observable<Tournament> {
    let url = this.baseUrl + 'tournaments/addTournamentsAsList';
    return this.http.post<any>(url, tournaments);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      alert(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
