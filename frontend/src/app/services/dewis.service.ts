import { Injectable } from '@angular/core';
import {Client, NgxSoapService} from "ngx-soap";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DewisService {

  client: Client;

  constructor(private soap: NgxSoapService) {
    this.soap.createClient('assets/dewis.wsdl').then(
      client => this.client = client
    );
  }

  findPlayerByName(firstName: string, lastName: string): Observable<any> {
    let body = {
      firstname: firstName,
      surename: lastName,
    };
    return this.client.call("searchByName", body);
  }

  findTournamentCardById(pid: number): Observable<any> {
    let body = {
      pid: pid,
    };
    return this.client.call("tournamentCardForId", body);
  }

  findTournamentsByPeriod(startDate: Date, endDate: Date) {
    let body = {
      since: startDate.toISOString(),
      until: endDate.toISOString(),
      bSubs: true,
    }
    return this.client.call("tournamentsByPeriod", body);
  }

}
