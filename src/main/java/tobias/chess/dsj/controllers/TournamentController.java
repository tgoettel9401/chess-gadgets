package tobias.chess.dsj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tobias.chess.dsj.models.Tournament;
import tobias.chess.dsj.repositories.TournamentRepository;

import java.util.List;

@RestController
public class TournamentController {

    private TournamentRepository tournamentRepository;

    @Autowired
    public TournamentController(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping("tournaments/deleteAllTournaments")
    public void deleteAllTournaments() {
        this.tournamentRepository.deleteAll();
    }

    @PostMapping("tournaments/addTournamentsAsList")
    public List<Tournament> addTournamentsAsList(@RequestBody List<Tournament> tournamentList) {
        tournamentList = this.tournamentRepository.saveAll(tournamentList);
        return tournamentList;
    }

    @GetMapping("tournaments/getByCodeAndName")
    public Tournament getByTcodeAndName(@RequestParam("tcode") String tcode, @RequestParam("tname")String tname) {
        return this.tournamentRepository.findFirstByTcodeAndAndTname(tcode, tname);
    }
}
