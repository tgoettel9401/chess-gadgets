package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateQuotaWithIntermediates {

    @JsonBackReference
    private State state;

    private Double membershipFigure;
    private Double calculationQuotaQuantity;
    private Double averageTeamPoints;
    private Double calculationQuotaQuality;
    private Double calculationQuotaBeforeHareNiemayer;
    private Double calculationQuotaAfterHareNiemayer;

    public String getStateName() {
        return state.getName();
    }

}
