package tobias.chess.dsj.models.quota;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class QuotaTournament {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    private Integer year;

    @OrderBy(value = "year desc")
    @OneToMany(mappedBy = "quotaTournament", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private Set<ImportedTournament> importedTournaments = new HashSet<>();

    @OrderBy(value = "figure desc")
    @OneToMany(mappedBy = "quotaTournament", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<MembershipFigure> membershipFigures = new ArrayList<>();

    public QuotaTournament(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

}
