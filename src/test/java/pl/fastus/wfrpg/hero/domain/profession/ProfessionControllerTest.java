package pl.fastus.wfrpg.hero.domain.profession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProfessionControllerTest {

    @Mock
    ProfessionService service;

    @InjectMocks
    ProfessionController controller;

    MockMvc mockMvc;

    List<Profession> professions;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        professions = getProfessionList();
    }

    @Test
    void getAllProfessions() throws Exception {
        BDDMockito.given(service.getProfessions()).willReturn(professions);

        mockMvc.perform(get("/professions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Profession1")))
                .andExpect(jsonPath("$[0].level", is("1")))
                .andExpect(jsonPath("$[0].availableForRaces[1]", is("Dwarf")))
                .andExpect(jsonPath("$[3].name", is("Profession4")));
    }

    @Test
    void getProfessionsByLvl() {
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
