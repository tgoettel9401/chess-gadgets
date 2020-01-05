package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private QuotaTournament quotaTournament;

    @ManyToOne
    @JsonBackReference
    private State state;

    private Double figure;

    public MembershipFigure(QuotaTournament quotaTournament, State state, Double figure) {
        this.quotaTournament = quotaTournament;
        this.state = state;
        this.figure = figure;
    }

    @JsonIgnore
    public StateFigure getStateFigure() {
        return new StateFigure(state, figure);
    }

    public String getStateName() {
        return this.getState().getName();
    }

}
