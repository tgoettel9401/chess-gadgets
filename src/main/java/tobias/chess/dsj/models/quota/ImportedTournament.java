package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class ImportedTournament {

    @Id
    @GeneratedValue
    private long id;

    private Integer year;

    @CreationTimestamp
    private LocalDateTime tsCreate;

    @UpdateTimestamp
    private LocalDateTime tsUpdate;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private QuotaTournament quotaTournament;

    @OrderBy(value = "place")
    @OneToMany(mappedBy = "importedTournament", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    private Set<ImportedTournamentEntry> importedTournamentEntries = new HashSet<>();

    public ImportedTournament(Integer year, QuotaTournament quotaTournament) {
        this.year = year;
        this.quotaTournament = quotaTournament;
    }
}
