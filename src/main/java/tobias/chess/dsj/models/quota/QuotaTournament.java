package tobias.chess.dsj.models.quota;

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
public class QuotaTournament {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    private Integer year;

    @OrderBy(value = "name")
    @OneToMany(mappedBy = "quotaTournament", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<ImportedTournament> importedTournaments = new HashSet<>();

    public QuotaTournament(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

}
