package pl.fastus.wfrpg.hero.domain.race;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    RaceRepository repository;

    @InjectMocks
    RaceService service;

    List<Race> races;

    @BeforeEach
    void setUp() {
        races = List.of(
                Race.builder().id(1L).name("Humans").skillNames(List.of("Skill1", "Skill2", "Skill3"))
                        .talentNames(List.of("Talent1","Talent2")).freeTalents("2").build(),
                Race.builder().id(1L).name("Dwarfs").skillNames(List.of("Skill1", "Skill2"))
                        .talentNames(List.of("Talent1","Talent2", "Talent3")).freeTalents("4").build()
        );

    }

    @Test
    void getAllRaces() {
        BDDMockito.given(repository.findAll()).willReturn(races);

        List<Race> allRaces = service.getAllRaces();

        assertNotNull(allRaces);
        assertEquals(2, allRaces.size());
        assertEquals(3, allRaces.get(0).getSkillNames().size());
    }
}
