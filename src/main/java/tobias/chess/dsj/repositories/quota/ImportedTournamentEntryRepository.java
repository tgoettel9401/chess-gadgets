package tobias.chess.dsj.repositories.quota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tobias.chess.dsj.models.quota.ImportedTournamentEntry;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4200/"})
public interface ImportedTournamentEntryRepository extends JpaRepository<ImportedTournamentEntry, Long> {
}
