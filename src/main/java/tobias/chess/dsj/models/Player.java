package tobias.chess.dsj.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Player {

    @Id @GeneratedValue
    private Long id;

    private Long pid;

    private String firstName;
    private String lastName;

    private Integer yearOfBirth =0;

    private String club = "Club";

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Integer[] ratings = new Integer[0];

    private Integer maxRating;
    private Integer minRating;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<PlayerTournament> tournaments;

    public Player(String firstName, String lastName, Integer yearOfBirth, String club) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.yearOfBirth = yearOfBirth;
        this.club = club;
        this.ratings = new Integer[0];
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setTournaments(List<PlayerTournament> tournaments) {
        this.tournaments = tournaments;
    }
}
