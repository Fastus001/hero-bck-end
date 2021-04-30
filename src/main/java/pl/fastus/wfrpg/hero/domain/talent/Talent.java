package pl.fastus.wfrpg.hero.domain.talent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Talent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String statNumber;
    private String test;

    @Lob
    private String description;
}
