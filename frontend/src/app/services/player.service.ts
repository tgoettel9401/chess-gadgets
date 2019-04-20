import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  getAllPlayers(): Observable<any> {
    let url = 'http://localhost:8080/players';
    return this.http.get(url);
  }
}
