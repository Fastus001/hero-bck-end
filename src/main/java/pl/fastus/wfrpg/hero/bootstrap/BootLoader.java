package pl.fastus.wfrpg.hero.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fastus.wfrpg.hero.domain.profession.Profession;
import pl.fastus.wfrpg.hero.domain.profession.ProfessionRepository;
import pl.fastus.wfrpg.hero.domain.race.Race;
import pl.fastus.wfrpg.hero.domain.race.RaceRepository;
import pl.fastus.wfrpg.hero.domain.skill.Skill;
import pl.fastus.wfrpg.hero.domain.skill.SkillRepository;
import pl.fastus.wfrpg.hero.domain.talent.Talent;
import pl.fastus.wfrpg.hero.domain.talent.TalentRepository;
import pl.fastus.wfrpg.hero.enums.SkillType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Component
public class BootLoader implements CommandLineRunner {

    private final SkillRepository skillRepository;
    private final TalentRepository talentRepository;
    private final RaceRepository raceRepository;
    private final ProfessionRepository professionRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSkills();
        loadTalents();
        loadRaces();
        loadProfessions();
    }

    private void loadSkills() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/static/skills2.txt"));
        lines.stream()
                .map(this::convertToSkill)
                .forEach(skillRepository::save);
    }

    private Skill convertToSkill(String line){
        String[] split = line.split(";");
        return Skill.builder()
                .name(split[0])
                .statNumber(split[1])
                .type(split[2].equals("podstawowa")? SkillType.PODSTAWOWA:SkillType.ZAAWANSOWANA)
                .build();
    }

    private void loadTalents() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/static/talents.txt"));
        lines.stream()
                .map(this::convertToTalent)
                .forEach(talentRepository::save);
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

    private void loadRaces() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/static/races.txt"));
        lines.stream()
                .map(this::convertToRace)
                .forEach(raceRepository::save);
    }

    private Race convertToRace(String line) {
        String[] raceSplit = line.split(";");
        List<Skill> skills = Stream.of(raceSplit[1].split(":"))
                .map(skillRepository::findByName)
                .collect(Collectors.toList());

        List<Talent> talents = Stream.of(raceSplit[2].split(":"))
                .map(talentRepository::findByName)
                .collect(Collectors.toList());

        return Race.builder()
                .name(raceSplit[0])
                .skills(skills)
                .talents(talents)
                .freeTalents(raceSplit[3])
                .stats(List.of(raceSplit[4].split(",")))
                .build();
    }

    private void loadProfessions() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/static/professions.txt"));
        lines.stream()
                .map(this::convertToProfession)
                .flatMap(Collection::stream)
                .forEach(professionRepository::save);
    }

    private List<Profession> convertToProfession(String line) {
        String[] split = line.split(";");
        List<Skill> skillList = Stream.of(split[4].split("/"))
                .map(skillRepository::findByName)
                .collect(Collectors.toList());

        List<Talent> talentList = Stream.of(split[5].split("/"))
                .map(talentRepository::findByName)
                .collect(Collectors.toList());

        Profession male = Profession.builder()
                .career(split[0])
                .name(split[1].split("/")[0])
                .path(split[2].split("/")[0])
                .availableForRaces(List.of(split[3].split(",")))
                .skills(skillList)
                .talents(talentList)
                .items(List.of(split[6].split(",")))
                .stats(List.of(split[7].split(",")))
                .level(split[8])
                .male(true)
                .build();

        Profession female = Profession.builder()
                .career(split[0])
                .name(split[1].split("/")[1])
                .path(split[2].split("/")[1])
                .availableForRaces(List.of(split[3].split(",")))
                .skills(skillList)
                .talents(talentList)
                .items(List.of(split[6].split(",")))
                .stats(List.of(split[7].split(",")))
                .level(split[8])
                .male(false)
                .build();
        return List.of(male,female);
    }
}
