package tobias.chess.dsj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tobias.chess.dsj.models.Player;
import tobias.chess.dsj.models.PlayerTournament;
import tobias.chess.dsj.models.Tournament;
import tobias.chess.dsj.repositories.PlayerRepository;
import tobias.chess.dsj.repositories.PlayerTournamentRepository;
import tobias.chess.dsj.repositories.TournamentRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
public class PlayerController {

    private PlayerRepository playerRepository;
    private TournamentRepository tournamentRepository;
    private PlayerTournamentRepository playerTournamentRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository, TournamentRepository tournamentRepository, PlayerTournamentRepository playerTournamentRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
        this.playerTournamentRepository = playerTournamentRepository;
    }

    @PatchMapping("players/{id}/addTournamentsAsList")
    public Player addTournamentsAsList(@PathVariable("id") Long id, @RequestBody List<PlayerTournament> tournamentList) {
        Player player = this.playerRepository.findById(id).get();
        List<PlayerTournament> playerTournamentList = new ArrayList<>();
        for(PlayerTournament tournament : tournamentList) {
            if (this.tournamentRepository.findFirstByTcodeAndAndTname(tournament.getTcode(), tournament.getTname()) != null) {
                Tournament tempTournament = this.tournamentRepository.findFirstByTcodeAndAndTname(tournament.getTcode(), tournament.getTname());
                tournament.setCalculatedOn(tempTournament.getCalculatedOn());
                tournament.setFinishedOn(tempTournament.getFinishedOn());
                tournament.setTid(tempTournament.getTid());
            }
            tournament.setPlayer(player);
            playerTournamentList.add(tournament);
        }
        playerTournamentRepository.saveAll(playerTournamentList);
        player.setTournaments(tournamentList);
        return player;
    }

    @GetMapping("players/{id}/getLatestTournamentBeforeDate")
    public PlayerTournament getLatestTournamentBeforeDate(
            @PathVariable("id") long id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        Player player = playerRepository.findById(id).get();
        return playerTournamentRepository.findFirstByPlayerAndFinishedOnBeforeOrderByFinishedOnDesc(player, date);
    }

    @GetMapping("players/{id}/deleteAllTournaments")
    public void deleteAllTournaments(
            @PathVariable("id") long id) {
        Player player = this.playerRepository.findById(id).get();
        this.playerTournamentRepository.deleteAllByPlayer(player);
    }

}
