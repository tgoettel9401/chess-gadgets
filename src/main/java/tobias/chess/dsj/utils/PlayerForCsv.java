package tobias.chess.dsj.utils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tobias.chess.dsj.models.Player;

@Data
@NoArgsConstructor
@JsonPropertyOrder({ "pid", "firstName", "lastName", "yearOfBirth", "club", "rating1", "rating2", "rating3", "rating4", "rating5", "rating6", "rating7", "rating8"})
public class PlayerForCsv {

    private Long pid;

    private String firstName;
    private String lastName;

    private Integer yearOfBirth;

    private String club;

    private Integer rating1 = 0;
    private Integer rating2 = 0;
    private Integer rating3 = 0;
    private Integer rating4 = 0;
    private Integer rating5 = 0;
    private Integer rating6 = 0;
    private Integer rating7 = 0;
    private Integer rating8 = 0;

    public PlayerForCsv(Player player) {
        this.pid = player.getPid();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.yearOfBirth = player.getYearOfBirth();
        this.club = player.getClub();
        this.rating1 = player.getRatings()[0];
        this.rating2 = player.getRatings()[1];
        this.rating3 = player.getRatings()[2];
        this.rating4 = player.getRatings()[3];
        this.rating5 = player.getRatings()[4];
        this.rating6 = player.getRatings()[5];
        this.rating7 = player.getRatings()[6];
        this.rating8 = player.getRatings()[7];
    }

    public Player returnAsPlayer() {
        Player player = new Player(this.firstName, this.lastName, this.yearOfBirth, this.club);
        player.setPid(this.pid);
        return player;
    }

}
