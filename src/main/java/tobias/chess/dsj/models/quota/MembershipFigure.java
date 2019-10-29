package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
public class MembershipFigure {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    @JsonBackReference
    private State state;

    @ManyToOne
    @JsonBackReference
    private QuotaTournament quotaTournament;

    private Integer figure;

    public MembershipFigure(State state, Integer figure, QuotaTournament quotaTournament) {
        this.state = state;
        this.figure = figure;
        this.quotaTournament = quotaTournament;
    }

    public String getStateName() {
        return state.getName();
    }

}
