package pl.fastus.wfrpg.hero.domain.profession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.fastus.wfrpg.hero.domain.skill.Skill;
import pl.fastus.wfrpg.hero.domain.talent.Talent;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Tom - 03.05.2021
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
public class Profession {

    @Id
    @GeneratedValue
    private Long id;

    private String career;
    private String name;
    private String path;
    private Boolean male;

    @ManyToMany
    private List<Skill> skills;

    @ManyToMany
    private List<Talent> talents;

    @ElementCollection
    private List<String> availableForRaces;

    @ElementCollection
    private List<String> stats;

    @ElementCollection
    private List<String> items;

    private String level;
}
