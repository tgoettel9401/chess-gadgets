package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class State {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private RegionalGroup regionalGroup;

    @OneToMany(mappedBy = "state", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<MembershipFigure> membershipFigures = new ArrayList<>();

    public State(String name, RegionalGroup regionalGroup) {
        this.name = name;
        this.regionalGroup = regionalGroup;
    }

}
