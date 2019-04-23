import {Component, OnInit, ViewChild} from '@angular/core';
import {Player} from "@angular/core/src/render3/interfaces/player";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {PlayerService} from "../services/player.service";
import {DewisService} from "../services/dewis.service";
import {DewisPlayer} from "../models/DewisPlayer";
import {Tournament} from "../models/Tournament";

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.scss']
})
export class PlayerListComponent implements OnInit {

  players: Player[];
  dewisPlayers: DewisPlayer[];

  // Table fields
  dataSource: MatTableDataSource<Player>;
  displayedColumns: string[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private playerService: PlayerService, private dewisService: DewisService) { }

  ngOnInit() {
    this.getPlayers();
    this.displayedColumns = ['name', 'pid', 'mayOld', 'julOld', 'sepOld', 'novOld', 'janNew', 'marNew', 'mayNew', 'buttons'];
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

  getPlayerDetails(element: any) {
    this.dewisService.findTournamentCardById(element.pid).subscribe(
      res => {
        let test = res.result.return.tournaments;
        let tournaments = [];
        for(let tournament of res.result.return.tournaments.item) {
          let tempTournament = new Tournament();
          tempTournament.tname = tournament.tname.$value;
          tempTournament.tcode = tournament.tcode.$value;
          tempTournament.ratingOld = tournament.ratingOld.$value;
          tempTournament.ratingNew = tournament.ratingNew.$value;
          tournaments.push(tempTournament);
        }
        element.tournaments = tournaments;
      }
    )
  }

  searchPlayer(element: any) {
    this.dewisService.findPlayerByName(element.firstName, element.lastName).subscribe(
      res => {
        // If only one player is found, this player can be set as the correct player.
        if (res.result.return.members.item.length == 1) {
          element.pid = res.result.return.member.item[0].pid;
        }

        // If more than one player is found, it is checked if by using birthyear the result is unique.
        else if (res.result.return.members.item.length > 1) {
          let counter = 0;
          let correctPlayers = [];
          for (let player of res.result.return.members.item) {
            let value = +player.yearOfBirth.$value;
            if (value == element.yearOfBirth) {
              correctPlayers.push(player);
              counter++;
            }

          }

          // If counter == 1 then now the result is unique.
          if (counter == 1) {
            element.pid = correctPlayers[0].pid.$value;
          }

          // If the result is not unique after using the birthyear, the club will be used as well.
          else {
            let correctPlayers2 = [];
            let counter2 = 0;
            for (let player of correctPlayers) {
              if (player.club == element.club) {
                correctPlayers2.push(player);
                counter2++;
              }
            }

            // If the counter is still > 1, then this information is logged.
            if (counter2 > 1)
              console.log("Counter is > 1, so first element chosen!");

            // Now it is assumed that the result is unique.
              else {
              element.pid = correctPlayers2[0].pid.$value;
            }
          }
        }
      },

  error => console.log(error),
      () => this.playerService.patchPlayer(element).subscribe(),
    )
  }


  importTournaments() {
    let startDate = new Date(2018, 4, 1);
    let endDate = new Date(2019, 3, 31);
    this.dewisService.findTournamentsByPeriod(startDate, endDate).subscribe(
      res => {
        let test = res.result.return.tournaments;
        for(let tournament of res.result.return.tournaments.item) {
          let test = tournament;
          for (let player of this.players) {
            let test2 = player.tournaments;
            for (let playerTournament of player.tournaments) {
              let test3 = playerTournament;
              if (playerTournament.tcode == tournament.tcode.$value) {
                playerTournament.calculated = tournament.computedOn.$value;
                playerTournament.end = tournament.finishedOn.$value;
              }
            }
          }
        }
      },
      error => console.log(error),
    );
    let test = "test";
    test = "done";
  }

  calculateRatings() {
    let mayOld = new Date (2018,4, 2);
    let julOld = new Date (2018,6,2);
    let sepOld = new Date (2018,8,2);
    let novOld = new Date (2018,10,2);
    let janNew = new Date (2019,0,2);
    let marNew = new Date (2019,2,2);
    let mayNew = new Date (2019,4,2);
    for(let player of this.players) {
      let mayOldTournament = 0;
      let julOldTournaemnt = 0;
      let sepOldTournament = 0;
      let novOldTournament = 0;
      let janNewTournament = 0;
      let marNewTournament = 0;
      let mayNewTournament = 0;
      for (let tournament of player.tournaments) {
        let test = (tournament.end > mayOldTournament.end && tournament.end < mayOld);
        if (tournament.end > mayOldTournament.end && tournament.end < mayOld)
          mayOldTournament = tournament;
      }
      player.mayOld = mayOldTournament.ratingNew;

    }
  }
}
