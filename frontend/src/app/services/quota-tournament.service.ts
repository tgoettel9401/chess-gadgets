import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class QuotaTournamentService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/api/';

  getAllQuotaTournaments(): Observable<any> {
    let url = this.baseUrl + 'quotaTournaments';
    return this.http.get(url);
  }

  getById(quotaTournamentId : number): Observable<any> {
    let url = this.baseUrl + 'quotaTournaments/' + quotaTournamentId;
    return this.http.get(url);
  }

  getAllImportedTournamentsForQuotaTournament(quotaTournamentId: number): Observable<any> {
    let url = this.baseUrl + 'quotaTournaments/' + quotaTournamentId + '/importedTournaments';
    return this.http.get(url);
  }

  getAllTournamentEntriesForImportedTournament(importedTournamentId: number): Observable<any> {
    let url = this.baseUrl + 'importedTournaments/' + importedTournamentId + '/importedTournamentEntries';
    return this.http.get(url);
  }

  getAllMembershipFiguresForQuotaTournament(quotaTournamentId: number): Observable<any> {
    let url = this.baseUrl + 'quotaTournaments/' + quotaTournamentId + '/membershipFigures';
    return this.http.get(url);
  }

}
