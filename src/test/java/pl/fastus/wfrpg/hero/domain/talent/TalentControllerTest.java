package pl.fastus.wfrpg.hero.domain.talent;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TalentControllerTest {

    @Mock
    TalentService talentService;

    @InjectMocks
    TalentController controller;

    MockMvc mockMvc;

    List<Talent> talentsToReturn;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        talentsToReturn = getTalentList();
    }

    @Test
    void getAllTalents() throws Exception {
        given(talentService.getAllTalents()).willReturn(talentsToReturn);

        mockMvc.perform(get("/talents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Talent1")))
                .andExpect(jsonPath("$[0].statNumber", is("1")))
                .andExpect(jsonPath("$[0].test", is("test1")))
                .andExpect(jsonPath("$[0].description", is("Description1")))
                .andExpect(jsonPath("$[3].id", is(4)));

        verify(talentService, times(1)).getAllTalents();
    }


    private List<Talent> getTalentList() {
        return List.of(
                Talent.builder().id(1L).name("Talent1").statNumber("1").test("test1").description("Description1").build(),
                Talent.builder().id(2L).name("Talent2").statNumber("2").test("test2").description("Description2").build(),
                Talent.builder().id(3L).name("Talent3").statNumber("3").test("test3").description("Description3").build(),
                Talent.builder().id(4L).name("Talent4").statNumber("4").test("test4").description("Description4").build()
        );
    }
}
