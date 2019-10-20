import { Component, OnInit } from '@angular/core';
import {QuotaTournamentService} from "../services/quota-tournament.service";
import {QuotaTournament} from "../models/QuotaTournament";

@Component({
  selector: 'app-quota',
  templateUrl: './quota.component.html',
  styleUrls: ['./quota.component.scss']
})
export class QuotaComponent implements OnInit {

  quotaTournaments: QuotaTournament[];

  constructor(private quotaTournamentService: QuotaTournamentService) { }

  ngOnInit() {
    this.getQuotaTournaments();
  }

  getQuotaTournaments() {
    this.quotaTournamentService.getAllQuotaTournaments().subscribe(
      data => this.quotaTournaments = data._embedded.quotaTournaments,
      error => console.log(error)

    )
  }

}
