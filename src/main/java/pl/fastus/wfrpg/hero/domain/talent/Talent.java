package pl.fastus.wfrpg.hero.domain.talent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String description;
}
