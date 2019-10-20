package tobias.chess.dsj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tobias.chess.dsj.models.playerInformation.Player;
import tobias.chess.dsj.models.playerInformation.PlayerTournament;
import tobias.chess.dsj.models.playerInformation.Tournament;
import tobias.chess.dsj.repositories.playerInformation.PlayerRepository;
import tobias.chess.dsj.repositories.playerInformation.PlayerTournamentRepository;
import tobias.chess.dsj.repositories.playerInformation.TournamentRepository;

import javax.transaction.Transactional;
import java.time.ZoneOffset;
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

    @PatchMapping("api/players/{id}/addTournamentsAsList")
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

    @GetMapping("api/players/{id}/getLatestTournamentBeforeDate")
    public PlayerTournament getLatestTournamentBeforeDate(
            @PathVariable("id") long id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        Player player = playerRepository.findById(id).get();
        return playerTournamentRepository.findFirstByPlayerAndFinishedOnBeforeOrderByFinishedOnDesc(player, date);
    }

    @GetMapping("api/players/{id}/deleteAllTournaments")
    public void deleteAllTournaments(
            @PathVariable("id") long id) {
        Player player = this.playerRepository.findById(id).get();
        this.playerTournamentRepository.deleteAllByPlayer(player);
    }

    @GetMapping("api/players/{id}/getRatings")
    public List<Integer> getRatings(
            @PathVariable("id") long id) {
        Player player = this.playerRepository.findById(id).get();
        List<Integer> ratings = new ArrayList<>();
        for (ZonedDateTime date : this.getDates()) {
            Integer tempRating = 99999;
            PlayerTournament tournament = this.playerTournamentRepository.findFirstByPlayerAndFinishedOnBeforeOrderByFinishedOnDesc(player, date);
            tempRating = tournament.getRatingNew();
            ratings.add(tempRating);
        }
        player.setRatings(ratings.toArray(Integer[]::new));
        this.getMaxRatingInLastYear(player.getId());
        this.getMinRatingInLastYear(player.getId());
        return ratings;
    }

    @GetMapping("api/players/{id}/getMaxRating")
    public Integer getMaxRatingInLastYear(
            @PathVariable("id") long id) {
        Player player = this.playerRepository.findById(id).get();
        ZonedDateTime startDate = ZonedDateTime.of(2018,5,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime endDate = ZonedDateTime.of(2019,5,1,0,0,0,0, ZoneOffset.UTC);
        PlayerTournament maxTournament = this.playerTournamentRepository.findFirstByPlayerAndFinishedOnAfterAndFinishedOnBeforeOrderByRatingNewDesc(player, startDate, endDate);
        player.setMaxRating(maxTournament.getRatingNew());
        return maxTournament.getRatingNew();
    }

    @GetMapping("api/players/{id}/getMinRating")
    public Integer getMinRatingInLastYear(
            @PathVariable("id") long id) {
        Player player = this.playerRepository.findById(id).get();
        ZonedDateTime startDate = ZonedDateTime.of(2018,5,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime endDate = ZonedDateTime.of(2019,5,1,0,0,0,0, ZoneOffset.UTC);
        PlayerTournament minTournament = this.playerTournamentRepository.findFirstByPlayerAndFinishedOnAfterAndFinishedOnBeforeOrderByRatingNewAsc(player, startDate, endDate);
        player.setMinRating(minTournament.getRatingNew());
        return minTournament.getRatingNew();
    }

    private List<ZonedDateTime> getDates() {
        List<ZonedDateTime> dates = new ArrayList<>();
        ZonedDateTime date1 = ZonedDateTime.of(2019,7,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime date2 = ZonedDateTime.of(2018,5,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime date3 = ZonedDateTime.of(2018,7,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime date4 = ZonedDateTime.of(2018,9,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime date5 = ZonedDateTime.of(2018,11,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime date6 = ZonedDateTime.of(2019,1, 1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime date7 = ZonedDateTime.of(2019,3,1,0,0,0,0, ZoneOffset.UTC);
        ZonedDateTime date8 = ZonedDateTime.of(2019,5,1,0,0,0,0, ZoneOffset.UTC);
        dates.add(date1);
        dates.add(date2);
        dates.add(date3);
        dates.add(date4);
        dates.add(date5);
        dates.add(date6);
        dates.add(date7);
        dates.add(date8);
        return dates;
    }

}
