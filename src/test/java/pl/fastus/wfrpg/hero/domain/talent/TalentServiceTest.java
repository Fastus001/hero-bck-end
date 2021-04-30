package pl.fastus.wfrpg.hero.domain.talent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TalentServiceTest {

    @Mock
    TalentRepository repository;

    @InjectMocks
    TalentService service;

    List<Talent> talents;

    @BeforeEach
    void setUp() {
        talents = getTalentList();
    }

    @Test
    void getAllTalents(){
        given(repository.findAll()).willReturn(talents);

        List<Talent> allTalents = service.getAllTalents();

        assertNotNull(allTalents);
        assertEquals(4, allTalents.size());
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
