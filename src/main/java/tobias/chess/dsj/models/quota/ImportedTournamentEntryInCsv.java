package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImportedTournamentEntryInCsv {

    @JsonProperty("Platz")
    private Integer place;

    @JsonProperty("Team")
    private String team;

    @JsonProperty("DWZ-Schnitt")
    private Integer avgDwz;

    @JsonProperty("Land")
    private String state;

    @JsonProperty("G")
    private Integer gamesWon;

    @JsonProperty("U")
    private Integer gamesDrawn;

    @JsonProperty("V")
    private Integer gamesLost;

    @JsonProperty("MP")
    private String points;

    public Double getPoints() {
        String tempPoints = points;
        if (tempPoints.contains(","))
            tempPoints = tempPoints.replace(",", ".");
        return Double.parseDouble(tempPoints);
    }

    @JsonProperty("SoBo")
    private String soberg;

    public Double getSoBerg() {
        String tempSoBerg = soberg;
        if (tempSoBerg.contains(","))
            tempSoBerg = tempSoBerg.replace(",", ".");
        return Double.parseDouble(tempSoBerg);
    }

    @JsonProperty("BP")
    private String boardPoints;

    public Double getBoardPoints() {
        String tempBoardPoints = boardPoints;
        if (tempBoardPoints.contains(","))
            tempBoardPoints = tempBoardPoints.replace(",", ".");
        return Double.parseDouble(tempBoardPoints);
    }
}
