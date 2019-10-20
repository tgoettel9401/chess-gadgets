import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ImportedTournament} from "../models/ImportedTournament";
import {QuotaTournamentService} from "../services/quota-tournament.service";
import {ImportedTournamentEntry} from "../models/ImportedTournamentEntry";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-quota-tournament-imported-tournaments-table',
  templateUrl: './quota-tournament-imported-tournaments-table.component.html',
  styleUrls: ['./quota-tournament-imported-tournaments-table.component.scss'],
  providers: [QuotaTournamentService],
})
export class QuotaTournamentImportedTournamentsTableComponent implements OnInit {

  @Input() importedTournament: ImportedTournament;

  importedTournamentEntries: ImportedTournamentEntry[];

  // Table fields
  dataSource: MatTableDataSource<ImportedTournamentEntry>;
  displayedColumns: string[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private quotaTournamentService: QuotaTournamentService,
  ) { }

  ngOnInit() {
    this.getImportedTournamentTable(this.importedTournament);
    this.displayedColumns = ["place", "team", "state", "regionalGroup", "avgDwz", "gamesWon", "gamesDrawn", "gamesLost", "points", "soBerg", "boardPoints"];
  }

  updateTable(importedTournamentEntries: any) {
    this.dataSource = new MatTableDataSource<ImportedTournamentEntry>(importedTournamentEntries);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getImportedTournamentTable(importedTournament: ImportedTournament) {
    this.quotaTournamentService.getAllTournamentEntriesForImportedTournament(importedTournament.id)
      .subscribe(
        data => this.importedTournamentEntries = data._embedded.importedTournamentEntries,
        error => console.log(error),
        () => this.updateTable(this.importedTournamentEntries)
      );
  }
}
