import {Component, OnInit, ViewChild} from '@angular/core';
import {Player} from "@angular/core/src/render3/interfaces/player";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {PlayerService} from "../services/player.service";

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.scss']
})
export class PlayerListComponent implements OnInit {

  players: Player[];

  // Table fields
  dataSource: MatTableDataSource<Player>;
  displayedColumns: string[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private playerService: PlayerService) { }

  ngOnInit() {
    this.getPlayers();
    this.displayedColumns = ['name'];
  }

  getPlayers() {
    this.playerService.getAllPlayers().subscribe(
      data => this.players = data._embedded.players,
      error => console.log(error),
      () => {
        this.dataSource = new MatTableDataSource<Player>(this.players);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    )
  }

}
