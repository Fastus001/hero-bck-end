package pl.fastus.wfrpg.hero.domain.race;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

        races = List.of(
                Race.builder().id(1L).name("Humans").skillNames(List.of("Skill1", "Skill2", "Skill3"))
                        .talentNames(List.of("Talent1","Talent2")).freeTalents("2")
                        .stats(List.of("20", "30","25")).build(),
                Race.builder().id(1L).name("Dwarfs").skillNames(List.of("Skill1", "Skill2"))
                        .talentNames(List.of("Talent1","Talent2", "Talent3")).freeTalents("4").build()
        );
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
}
