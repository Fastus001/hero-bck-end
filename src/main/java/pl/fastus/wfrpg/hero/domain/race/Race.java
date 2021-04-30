package pl.fastus.wfrpg.hero.domain.race;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
public class Race {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ElementCollection
    private List<String> skillNames;

    @ElementCollection
    private List<String> talentNames;

    private String freeTalents;
}
