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

    private String name;

    public Player(String name) {
        this.name = name;
    }

}
