package pl.fastus.wfrpg.hero.domain.profession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProfessionServiceTest {

    @Mock
    ProfessionRepository repository;

    @InjectMocks
    ProfessionService service;

    List<Profession> professionsToReturn;

    @BeforeEach
    void setUp() {
        professionsToReturn = getProfessionList();
    }

    @Test
    void getProfessions() {
        given(repository.findAll()).willReturn(professionsToReturn);

        List<Profession> professions = service.getProfessions();

        assertNotNull(professions);
        assertEquals(4, professions.size());
        assertEquals(2, professions.get(0).getAvailableForRaces().size());
    }

    @Test
    void getByLvl() {
        given(repository.findAllByLevel(any()))
                .willReturn(List.of(professionsToReturn.get(2), professionsToReturn.get(3)));

        List<Profession> professionByLevel = service.getByLvl(3);

        assertNotNull(professionByLevel);
        assertEquals(2, professionByLevel.size());
    }

    private List<Profession> getProfessionList() {
        return List.of(
                Profession.builder().id(1L).name("Profession1").availableForRaces(List.of("Human", "Dwarf")).level("1")
                        .build(),
                Profession.builder().id(2L).name("Profession2").availableForRaces(List.of("Human", "Dwarf", "Elf"))
                        .level("2").build(),
                Profession.builder().id(3L).name("Profession3").availableForRaces(List.of("Halfling", "Dwarf", "Elf"))
                        .level("3").build(),
                Profession.builder().id(4L).name("Profession4").availableForRaces(List.of("Halfling", "Dwarf", "Elf"))
                        .level("3").build()
        );
    }
}
