package tobias.chess.dsj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tobias.chess.dsj.models.Player;
import tobias.chess.dsj.models.PlayerTournament;

import java.time.ZonedDateTime;
import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4200/"})
public interface PlayerTournamentRepository extends JpaRepository<PlayerTournament, Long> {
    PlayerTournament findFirstByPlayerAndFinishedOnBeforeOrderByFinishedOnDesc(Player player, ZonedDateTime endDate);
    PlayerTournament findFirstByFinishedOnBeforeOrderByFinishedOnDesc(ZonedDateTime endDate);
    void deleteAllByPlayer(Player player);
    List<PlayerTournament> findByPlayer(Player player);
}
