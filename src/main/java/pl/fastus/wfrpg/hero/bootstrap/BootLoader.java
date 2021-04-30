package pl.fastus.wfrpg.hero.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fastus.wfrpg.hero.domain.skill.Skill;
import pl.fastus.wfrpg.hero.domain.skill.SkillRepository;
import pl.fastus.wfrpg.hero.domain.talent.Talent;
import pl.fastus.wfrpg.hero.domain.talent.TalentRepository;
import pl.fastus.wfrpg.hero.enums.SkillType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class BootLoader implements CommandLineRunner {

    private final SkillRepository skillRepository;
    private final TalentRepository talentRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSkills();
        loadTalents();

    }

    private void loadSkills() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/static/skills2.txt"));
        lines.stream()
                .map(this::convertToSkill)
                .forEach(skillRepository::save);
    }

    private void loadTalents() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/static/talents.txt"));
        lines.stream()
                .map(this::convertToTalent)
                .forEach(talentRepository::save);
    }

    private Skill convertToSkill(String line){
        String[] split = line.split(";");
        return Skill.builder()
                .name(split[0])
                .statNumber(split[1])
                .type(split[2].equals("podstawowa")? SkillType.BASIC:SkillType.ADVANCED)
                .build();
    }

    private Talent convertToTalent(String line){
        String[] split = line.split(";");
        return Talent.builder()
                .name(split[0])
                .statNumber(split[1])
                .test(split[2])
                .description(split[3])
                .build();
    }
}
