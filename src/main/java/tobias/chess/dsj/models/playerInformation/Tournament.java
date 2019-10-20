package tobias.chess.dsj.models.playerInformation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
public class Tournament {

    @Id @GeneratedValue
    private Long id;

    private Long tid;

    private String tcode;

    private String tname;

    private ZonedDateTime calculatedOn;

    private ZonedDateTime finishedOn;

    private Integer ratingOld;

    private Integer ratingNew;
}
