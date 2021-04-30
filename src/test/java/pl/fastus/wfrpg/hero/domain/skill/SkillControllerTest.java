package pl.fastus.wfrpg.hero.domain.skill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.fastus.wfrpg.hero.enums.SkillType;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SkillControllerTest {

    @Mock
    SkillService service;

    @InjectMocks
    SkillController controller;

    MockMvc mockMvc;

    List<Skill> skills;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        skills = List.of(
                new Skill("A", "0", SkillType.BASIC),
                new Skill("B", "1", SkillType.ADVANCED)
        );
    }

    @Test
    void getAllSkills() throws Exception {
        given(service.getAllSkills()).willReturn(skills);

        mockMvc.perform(get("/skills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("A")))
                .andExpect(jsonPath("$[0].statNumber", is("0")))
                .andExpect(jsonPath("$[0].type", is("BASIC")))
                .andExpect(jsonPath("$[1].name", is("B")))
                .andExpect(jsonPath("$[1].statNumber", is("1")))
                .andExpect(jsonPath("$[1].type", is("ADVANCED")));

        verify(service, times(1)).getAllSkills();
    }

    @Test
    void getBySkillName() throws Exception {
        given(service.findSkillByName(any())).willReturn(skills.get(0));

        mockMvc.perform(get("/skills/A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("A")))
                .andExpect(jsonPath("$.statNumber", is("0")))
                .andExpect(jsonPath("$.type", is("BASIC")));

        verify(service, times(1)).findSkillByName(any());
    }
}
