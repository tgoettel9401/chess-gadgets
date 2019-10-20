import {Component, OnInit, SystemJsNgModuleLoader} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuotaTournamentService} from "../services/quota-tournament.service";
import {QuotaTournament} from "../models/QuotaTournament";

@Component({
  selector: 'app-quota-tournament-details',
  templateUrl: './quota-tournament-details.component.html',
  styleUrls: ['./quota-tournament-details.component.scss'],
  providers: [QuotaTournamentService],
})
export class QuotaTournamentDetailsComponent implements OnInit {

  quotaTournament: QuotaTournament;

  constructor(
    private route: ActivatedRoute,
    private quotaTournamentService: QuotaTournamentService,
  ) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.getQuotaTournament(id);
  }

  getQuotaTournament(id: number) {
    this.quotaTournamentService.getById(id)
      .subscribe(
        quotaTournament => this.quotaTournament = quotaTournament,
        error => console.log(error),
      );
  }

}
