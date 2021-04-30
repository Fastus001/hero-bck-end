package pl.fastus.wfrpg.hero.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Builder
    public Talent(String name, String statNumber, String test, String description) {
        this.name = name;
        this.statNumber = statNumber;
        this.test = test;
        this.description = description;
    }
}
