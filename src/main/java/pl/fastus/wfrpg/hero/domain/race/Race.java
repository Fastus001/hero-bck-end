package pl.fastus.wfrpg.hero.domain.race;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.fastus.wfrpg.hero.domain.skill.Skill;
import pl.fastus.wfrpg.hero.domain.talent.Talent;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    @ManyToMany
    private List<Talent> talents = new ArrayList<>();

    private String freeTalents;

    @ElementCollection
    private List<String> stats;
}
