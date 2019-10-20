import {Component, Input, OnInit} from '@angular/core';
import {QuotaTournament} from "../models/QuotaTournament";
import {ImportedTournament} from "../models/ImportedTournament";
import {QuotaTournamentService} from "../services/quota-tournament.service";

@Component({
  selector: 'app-quota-tournament-imported-tournaments',
  templateUrl: './quota-tournament-imported-tournaments.component.html',
  styleUrls: ['./quota-tournament-imported-tournaments.component.scss'],
  providers: [QuotaTournamentService],
})
export class QuotaTournamentImportedTournamentsComponent implements OnInit {

  @Input() quotaTournament: QuotaTournament;

  importedTournaments: ImportedTournament[];

  constructor(
    private quotaTournamentService: QuotaTournamentService,
  ) { }

  ngOnInit() {
    this.getImportedTournaments(this.quotaTournament);
  }

  getImportedTournaments(quotaTournament: QuotaTournament) {
    this.quotaTournamentService.getAllImportedTournamentsForQuotaTournament(quotaTournament.id)
      .subscribe(
      data => this.importedTournaments = data._embedded.importedTournaments,
      error => console.log(error),
        () => console.log(this.importedTournaments)
    );
  }



}
