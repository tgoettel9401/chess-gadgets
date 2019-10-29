import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {QuotaTournament} from "../models/QuotaTournament";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {MembershipFigure} from "../models/MembershipFigure";
import {QuotaTournamentService} from "../services/quota-tournament.service";

@Component({
  selector: 'app-quota-tournament-membership-figures',
  templateUrl: './quota-tournament-membership-figures.component.html',
  styleUrls: ['./quota-tournament-membership-figures.component.scss'],
  providers: [QuotaTournamentService],
})
export class QuotaTournamentMembershipFiguresComponent implements OnInit {

  @Input() quotaTournament: QuotaTournament;

  membershipFigures: MembershipFigure[];

  // Table fields
  dataSource: MatTableDataSource<MembershipFigure>;
  displayedColumns: string[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private quotaTournamentService: QuotaTournamentService,
  ) { }

  ngOnInit() {
    this.getMembershipFiguresTable(this.quotaTournament);
    this.displayedColumns = ["state", "figure"];
  }

  updateTable(membershipFigures: any) {
    this.dataSource = new MatTableDataSource<MembershipFigure>(membershipFigures);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getMembershipFiguresTable(quotaTournament: QuotaTournament) {
    this.quotaTournamentService.getAllMembershipFiguresForQuotaTournament(quotaTournament.id)
      .subscribe(
        data => this.membershipFigures = data._embedded.membershipFigures,
        error => console.log(error),
        () => this.updateTable(this.membershipFigures)
      );
  }

}
