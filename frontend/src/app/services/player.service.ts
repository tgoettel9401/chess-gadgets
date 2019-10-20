import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Player} from "../models/Player";
import {catchError, tap} from "rxjs/operators";
import {Tournament} from "../models/Tournament";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/api/';

  getAllPlayers(): Observable<any> {
    let url = this.baseUrl + 'players?size=1000';
    return this.http.get(url);
  }

  addTournamentsToPlayer(player: Player, tournaments: Tournament[]): Observable<Player> {
    let url = this.baseUrl + 'players/' + player.id + '/addTournamentsAsList';
    return this.http.patch<Player>(url, tournaments);
  }

  patchPlayer(player: Player): Observable<Player> {
    let url = this.baseUrl + 'players/' + player.id;
    return this.http.patch<Player>(url, player)
      .pipe(
        tap((changedPlayer: Player) => console.log('Changed player with id=' + changedPlayer.id)),
        catchError(this.handleError<Player>('changePlayer'))
      );
  }

  getRatings(player: Player): Observable<any> {
    let url = this.baseUrl + 'players/' + player.id + '/getRatings';
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
