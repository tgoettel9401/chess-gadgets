import {Component, OnInit, ViewChild} from '@angular/core';
import {Player} from "@angular/core/src/render3/interfaces/player";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {PlayerService} from "../services/player.service";
import {DewisService} from "../services/dewis.service";
import {DewisPlayer} from "../models/DewisPlayer";
import {Tournament} from "../models/Tournament";
import {TournamentService} from "../services/tournament.service";
import {log} from "util";
import {Observable} from "rxjs";

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.scss']
})
export class PlayerListComponent implements OnInit {

  players: Player[];
  dewisPlayers: DewisPlayer[];

  ratings = []; // 8 Ratings due to 8 columns
  dates = []; // 8 Dates due to 8 columns

  // Table fields
  dataSource: MatTableDataSource<Player>;
  displayedColumns: string[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private playerService: PlayerService, private dewisService: DewisService, private tournamentService: TournamentService) { }

  ngOnInit() {
    this.getPlayers();
    this.displayedColumns = ['name', 'pid', 'mayOld', 'julOld', 'sepOld', 'novOld', 'janNew', 'marNew', 'mayNew', 'buttons'];
  }

  getPlayers() {
    this.playerService.getAllPlayers().subscribe(
      data => this.players = data._embedded.players,
      error => console.log(error),
      () => this.updateTable(this.players)
    )
  }

  updateTable(players: any) {
    this.dataSource = new MatTableDataSource<Player>(players);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
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
        this.playerService.addTournamentsToPlayer(element, tournaments).subscribe();
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

  importTournamentsAndSave() {
    let startDate = new Date(2018,0,1);
    let endDate = new Date(2019, 0, 1);
    this.tournamentService.deleteAllTournaments().subscribe(
      () => {},
      error => console.log(error),
      () => {
        this.dewisService.findTournamentsByPeriod(startDate, endDate).subscribe(
          res => {
            let tournaments = [];
              for(let tournament of res.result.return.tournaments.item) {
              let tournamentForImport = new Tournament();
              tournamentForImport.calculatedOn = new Date(tournament.computedOn.$value);
              tournamentForImport.finishedOn = new Date(tournament.finishedOn.$value);
              tournamentForImport.tcode = tournament.tcode.$value;
              tournamentForImport.tname = tournament.tname.$value;
              tournamentForImport.tid = tournament.tid.$value;
              // Rating old and Rating new are still empty because they are only available on Tournament attached with player.
              //this.tournamentService.postTournament(tournamentForImport).subscribe();
                tournaments.push(tournamentForImport);
            }
              this.tournamentService.postMultipleTournaments(tournaments).subscribe();

          },
          error => console.log(error),
          () => {
            let startDate = new Date(2019,0,1);
            let endDate = new Date(2020, 0, 1);
            this.dewisService.findTournamentsByPeriod(startDate, endDate).subscribe(
              res => {
                let tournaments = [];
                for(let tournament of res.result.return.tournaments.item) {
                  let tournamentForImport = new Tournament();
                  tournamentForImport.calculatedOn = new Date(tournament.computedOn.$value);
                  tournamentForImport.finishedOn = new Date(tournament.finishedOn.$value);
                  tournamentForImport.tcode = tournament.tcode.$value;
                  tournamentForImport.tname = tournament.tname.$value;
                  tournamentForImport.tid = tournament.tid.$value;
                  // Rating old and Rating new are still empty because they are only available on Tournament attached with player.
                  //this.tournamentService.postTournament(tournamentForImport).subscribe();
                  tournaments.push(tournamentForImport);
                }
                this.tournamentService.postMultipleTournaments(tournaments).subscribe();
              },
              error => console.log(error),
              () => {}
            );
          }
        );
      }
    );
  }

  calculateRatings() {
    let players = [];
    let newPlayers = [];
    this.playerService.getAllPlayers().subscribe(
      data => players = data._embedded.players,
      error => console.log(error),
    () => {
        for(let player of players) {
          this.playerService.getRatings(player).subscribe(
            data => player.ratings = data,
            error => console.log(error),
            () => {
              newPlayers.push(player);
              this.players = newPlayers;
              this.updateTable(newPlayers);
            }
          )
        }
    }
    );
  }

  importPlayerIds() {
    let players = [];
    this.playerService.getAllPlayers().subscribe(
      data => players = data._embedded.players,
      error => console.log(error),
      () => {
        for (let playerOut of players) {
          this.dewisService.findPlayerByName(playerOut.firstName, playerOut.lastName).subscribe(
            res => {
              // If only one player is found, this player can be set as the correct player.
              if (res.result.return.members.item.length == 1) {
                playerOut.pid = res.result.return.member.item[0].pid;
              }

              // If more than one player is found, it is checked if by using birthyear the result is unique.
              else if (res.result.return.members.item.length > 1) {
                let counter = 0;
                let correctPlayers = [];
                for (let player of res.result.return.members.item) {
                  let value = +player.yearOfBirth.$value;
                  if (value == playerOut.yearOfBirth) {
                    correctPlayers.push(player);
                    counter++;
                  }

                }

                // If counter == 1 then now the result is unique.
                if (counter == 1) {
                  playerOut.pid = correctPlayers[0].pid.$value;
                }

                // If the result is not unique after using the birthyear, the club will be used as well.
                else {
                  let correctPlayers2 = [];
                  let counter2 = 0;
                  for (let player of correctPlayers) {
                    if (player.club == playerOut.club) {
                      correctPlayers2.push(player);
                      counter2++;
                    }
                  }

                  // If the counter is still > 1, then this information is logged.
                  if (counter2 > 1)
                    console.log("Counter is > 1, so first element chosen!");

                  // Now it is assumed that the result is unique.
                  else {
                    playerOut.pid = correctPlayers2[0].pid.$value;
                  }
                }
              }
            },

            error => console.log(error),
            () => {
              this.playerService.patchPlayer(playerOut).subscribe();
              this.updateTable(players);
            },
          )
        }
      }
    )

  }

  importPlayerDetails() {
    let players = [];
    this.playerService.getAllPlayers().subscribe(
      data => players = data._embedded.players,
      error => console.log(error),
      () => {
        for (let playerOut of players) {
          this.dewisService.findTournamentCardById(playerOut.pid).subscribe(
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
              playerOut.tournaments = tournaments;
              this.playerService.addTournamentsToPlayer(playerOut, tournaments).subscribe();
            }
          )
        }
      }
    )
  }
}
