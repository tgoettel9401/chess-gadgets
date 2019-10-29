package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class RegionalGroup {

    @Id @GeneratedValue
    private long id;

    private String name;

    @OrderBy(value = "name")
    @OneToMany(mappedBy = "regionalGroup", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonManagedReference
    private Set<State> states = new HashSet<>();

    public RegionalGroup(String name) {
        this.name = name;
    }

}
