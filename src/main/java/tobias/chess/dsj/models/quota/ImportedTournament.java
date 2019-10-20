package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

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

    public ImportedTournament(String name, QuotaTournament quotaTournament) {
        this.name = name;
        this.quotaTournament = quotaTournament;
    }
}
