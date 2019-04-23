package tobias.chess.dsj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tobias.chess.dsj.models.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
