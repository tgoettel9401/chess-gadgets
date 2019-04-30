package tobias.chess.dsj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tobias.chess.dsj.models.Player;
import tobias.chess.dsj.models.PlayerTournament;

import java.time.ZonedDateTime;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4200/"})
public interface PlayerTournamentRepository extends JpaRepository<PlayerTournament, Long> {
    PlayerTournament findFirstByPlayerAndFinishedOnBeforeOrderByFinishedOnDesc(Player player, ZonedDateTime endDate);
    void deleteAllByPlayer(Player player);
    PlayerTournament findFirstByPlayerAndFinishedOnAfterAndFinishedOnBeforeOrderByRatingNewDesc(Player player, ZonedDateTime startDate, ZonedDateTime endDate);
    PlayerTournament findFirstByPlayerAndFinishedOnAfterAndFinishedOnBeforeOrderByRatingNewAsc(Player player, ZonedDateTime startDate, ZonedDateTime endDate);
}
