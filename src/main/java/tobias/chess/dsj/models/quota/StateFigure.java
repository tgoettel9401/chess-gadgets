package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateFigure {

    @JsonBackReference
    private State state;

    private Double figure;

    public StateFigure(State state, Double figure) {
        this.state = state;
        this.figure = figure;
    }

    public String getStateName() {
        return state.getName();
    }

}
