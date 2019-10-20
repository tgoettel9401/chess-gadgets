package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class State {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityReference(alwaysAsId = true)
    private RegionalGroup regionalGroup;

    public State(String name, RegionalGroup regionalGroup) {
        this.name = name;
        this.regionalGroup = regionalGroup;
    }

}
