package pl.fastus.wfrpg.hero.domain.skill;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.fastus.wfrpg.hero.enums.SkillType;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Skill {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String statNumber;

    @Enumerated(EnumType.STRING)
    private SkillType type;

    @Builder
    public Skill(String name, String statNumber, SkillType type) {
        this.name = name;
        this.statNumber = statNumber;
        this.type = type;
    }
}
