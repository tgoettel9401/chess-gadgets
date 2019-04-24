package tobias.chess.dsj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tobias.chess.dsj.models.Tournament;

import java.util.Date;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4200/"})
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Tournament findFirstByTcodeAndAndTname(String tcode, String tname);
}
