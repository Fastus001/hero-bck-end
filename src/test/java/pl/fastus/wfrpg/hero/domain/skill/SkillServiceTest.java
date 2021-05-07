package pl.fastus.wfrpg.hero.domain.skill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.fastus.wfrpg.hero.enums.SkillType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    SkillRepository skillRepository;

    @InjectMocks
    SkillService skillService;

    List<Skill> skills;

    @BeforeEach
    void setBefore(){
        skills = List.of(
                new Skill("A", "0", SkillType.PODSTAWOWA),
                new Skill("B", "1", SkillType.ZAAWANSOWANA)
        );
    }

    @Test
    void getAllSkills() {

        given(skillRepository.findAll()).willReturn(skills);

        List<Skill> returnedSkills = skillService.getAllSkills();

        assertNotNull(returnedSkills);
        assertEquals(2, returnedSkills.size());
    }

    @Test
    void findSkillByName() {
        given(skillRepository.findByName(any())).willReturn(skills.get(0));

        Skill skillByName = skillService.findSkillByName("A");
        assertNotNull(skillByName);
        assertEquals("A", skillByName.getName());
    }
}
