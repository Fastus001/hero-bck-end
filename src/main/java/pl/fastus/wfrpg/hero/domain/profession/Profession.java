package pl.fastus.wfrpg.hero.domain.profession;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by Tom - 03.05.2021
 */
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
public class Profession {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String path;
    private Integer level;

    @ElementCollection
    private List<String> skillNames;

    @ElementCollection
    private List<String> talentNames;

    @ElementCollection
    private List<String> availableForRaces;

    @ElementCollection
    private List<Integer> stats;

    @ElementCollection
    private List<String> items;
}
