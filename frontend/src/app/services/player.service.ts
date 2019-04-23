import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {DewisService} from "./dewis.service";
import {Player} from "../models/Player";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient, private dewisService: DewisService) { }

  getAllPlayers(): Observable<any> {
    let url = 'http://localhost:8080/players';
    return this.http.get(url);
  }

  patchPlayer(player: Player): Observable<Player> {
    let url = 'http://localhost:8080/players/' + player.id;
    return this.http.patch<Player>(url, player)
      .pipe(
        tap((changedPlayer: Player) => console.log('Changed player with id=' + changedPlayer.id)),
        catchError(this.handleError<Player>('changePlayer'))
      );
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
