package tobias.chess.dsj.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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

    private Integer yearOfBirth;

    private String club;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<PlayerTournament> tournaments;

    public Player(String firstName, String lastName, Integer yearOfBirth, String club) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.yearOfBirth = yearOfBirth;
        this.club = club;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setTournaments(List<PlayerTournament> tournaments) {
        this.tournaments = tournaments;
    }



}
