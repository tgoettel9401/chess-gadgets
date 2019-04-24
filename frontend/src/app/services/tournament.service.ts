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

  deleteAllTournaments(): Observable<any> {
    let url = 'http://localhost:8080/tournaments/deleteAllTournaments';
    return this.http.get(url);
  }

  postMultipleTournaments(tournaments: Tournament[]): Observable<Tournament> {
    let url = 'http://localhost:8080/tournaments/addTournamentsAsList';
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
