import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {DewisService} from "./dewis.service";
import {Player} from "../models/Player";
import {catchError, tap} from "rxjs/operators";
import {Tournament} from "../models/Tournament";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  getAllPlayers(): Observable<any> {
    let url = 'http://localhost:8080/players';
    return this.http.get(url);
  }

  addTournamentsToPlayer(player: Player, tournaments: Tournament[]): Observable<Player> {
    let url = 'http://localhost:8080/players/' + player.id + '/addTournamentsAsList';
    return this.http.patch<Player>(url, tournaments);
  }

  patchPlayer(player: Player): Observable<Player> {
    let url = 'http://localhost:8080/players/' + player.id;
    return this.http.patch<Player>(url, player)
      .pipe(
        tap((changedPlayer: Player) => console.log('Changed player with id=' + changedPlayer.id)),
        catchError(this.handleError<Player>('changePlayer'))
      );
  }

  getTournamentsBetween(player: Player): Observable<any> {
    let startDate = new Date (2019,0,1);
    let endDate = new Date (2019,5,23);
    //let url = encodeURI('http://localhost:8080/players/' + player.id + '/getTournamentsBetween?startDate=' + startDate + '&endDate=' + endDate);
    let url = 'http://localhost:8080/players/' + player.id + '/getTournamentsBetween?startDate=' + startDate.toISOString() + '&endDate=' + endDate.toISOString();
    return this.http.get(url);
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
