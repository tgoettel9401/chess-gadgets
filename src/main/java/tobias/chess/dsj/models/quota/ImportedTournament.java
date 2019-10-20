package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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

    private String name;

    @CreationTimestamp
    private LocalDateTime tsCreate;

    @UpdateTimestamp
    private LocalDateTime tsUpdate;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityReference(alwaysAsId = true)
    private QuotaTournament quotaTournament;

    @OrderBy(value = "place")
    @OneToMany(mappedBy = "importedTournament", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<ImportedTournamentEntry> importedTournamentEntries = new HashSet<>();

    public ImportedTournament(String name, QuotaTournament quotaTournament) {
        this.name = name;
        this.quotaTournament = quotaTournament;
    }
}
