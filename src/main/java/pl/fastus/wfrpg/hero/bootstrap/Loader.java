package pl.fastus.wfrpg.hero.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fastus.wfrpg.hero.domain.skill.Skill;
import pl.fastus.wfrpg.hero.domain.skill.SkillRepository;
import pl.fastus.wfrpg.hero.enums.SkillType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class Loader implements CommandLineRunner {

    private final SkillRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/skills2.txt"));
        lines.stream()
                .map(this::convertToSkill)
                .forEach(repository::save);

    }

    private Skill convertToSkill(String line){
        String[] split = line.split(";");
        return Skill.builder()
                .name(split[0])
                .statNumber(split[1])
                .type(split[2].equals("podstawowa")? SkillType.BASIC:SkillType.ADVANCED)
                .build();
    }
}
