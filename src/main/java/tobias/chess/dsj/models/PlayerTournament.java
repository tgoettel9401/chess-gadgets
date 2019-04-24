package tobias.chess.dsj.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
public class PlayerTournament {

    @Id @GeneratedValue
    private Long id;

    private Long tid;

    private String tcode;

    private String tname;

    private ZonedDateTime calculatedOn;

    private ZonedDateTime finishedOn;

    private Integer ratingOld;

    private Integer ratingNew;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Player player;
}
