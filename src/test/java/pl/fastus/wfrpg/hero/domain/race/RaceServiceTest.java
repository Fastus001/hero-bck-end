package pl.fastus.wfrpg.hero.domain.race;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.fastus.wfrpg.hero.domain.skill.Skill;
import pl.fastus.wfrpg.hero.domain.talent.Talent;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    RaceRepository repository;

    @InjectMocks
    RaceService service;

    List<Race> races;

    @BeforeEach
    void setUp() {
        races = getRaces();
    }

    @Test
    void getAllRaces() {
        BDDMockito.given(repository.findAll()).willReturn(races);

        List<Race> allRaces = service.getAllRaces();

        assertNotNull(allRaces);
        assertEquals(2, allRaces.size());
        assertEquals(3, allRaces.get(0).getSkills().size());
    }

    private List<Race> getRaces() {
        return List.of(
                Race.builder().id(1L).name("Humans")
                        .skills(List.of(
                                Skill.builder().name("Skill1").build(),
                                Skill.builder().name("Skill2").build(),
                                Skill.builder().name("Skill3").build()))
                        .talents(List.of(
                                Talent.builder().name("Talent1").build(),
                                Talent.builder().name("Talent2").build()
                                )).freeTalents("2").build(),
                Race.builder().id(1L).name("Dwarfs")
                        .skills(List.of(
                                Skill.builder().name("Skill1").build(),
                                Skill.builder().name("Skill2").build()
                        ))
                        .talents(List.of(
                                Talent.builder().name("Talent1").build(),
                                Talent.builder().name("Talent2").build(),
                                Talent.builder().name("Talent3").build()
                        ))
                        .freeTalents("4").build()
        );
    }
}
