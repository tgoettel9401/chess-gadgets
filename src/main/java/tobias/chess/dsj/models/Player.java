package tobias.chess.dsj.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Player(String firstName, String lastName, Integer yearOfBirth, String club) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.yearOfBirth = yearOfBirth;
        this.club = club;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

}
