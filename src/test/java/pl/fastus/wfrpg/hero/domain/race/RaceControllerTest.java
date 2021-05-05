package pl.fastus.wfrpg.hero.domain.race;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.fastus.wfrpg.hero.domain.skill.Skill;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RaceControllerTest {

    @Mock
    RaceService raceService;

    @InjectMocks
    RaceController controller;

    MockMvc mockMvc;

    List<Race> races;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        races = getRaces();
    }

    @Test
    void getAllRaces() throws Exception {
        given(raceService.getAllRaces()).willReturn(races);

        mockMvc.perform(get("/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Humans")))
                .andExpect(jsonPath("$[0].stats[0]", is("20")))
                .andExpect(jsonPath("$[1].name", is("Dwarfs")));
    }

    private List<Race> getRaces() {
        return List.of(
                Race.builder().id(1L).name("Humans")
                        .skills(List.of(
                                Skill.builder().name("Skill1").build(),
                                Skill.builder().name("Skill2").build(),
                                Skill.builder().name("Skill3").build()))
                        .talentNames(List.of("Talent1", "Talent2")).freeTalents("2")
                        .stats(List.of("20", "30", "25")).build(),
                Race.builder().id(1L).name("Dwarfs")
                        .skills(List.of(
                                Skill.builder().name("Skill1").build(),
                                Skill.builder().name("Skill2").build(),
                                Skill.builder().name("Skill3").build()))
                        .talentNames(List.of("Talent1", "Talent2", "Talent3")).freeTalents("4").build()
        );
    }
}
