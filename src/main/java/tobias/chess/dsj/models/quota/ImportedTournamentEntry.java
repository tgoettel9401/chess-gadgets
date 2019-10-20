package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class ImportedTournamentEntry {

    @Id @GeneratedValue
    private long id;

    private Integer place;

    private String team;

    private Integer avgDwz;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityReference(alwaysAsId = true)
    private State state;

    private Integer gamesWon;

    private Integer gamesDrawn;

    private Integer gamesLost;

    private Double points;

    private Double soBerg;

    private Double boardPoints;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityReference(alwaysAsId = true)
    private ImportedTournament importedTournament;

    private RegionalGroup getRegionalGroup() {
        return state.getRegionalGroup();
    }

    public String getStateName() {
        return this.getState().getName();
    }

    public String getRegionalGroupName() {
        return this.getRegionalGroup().getName();
    }

}
