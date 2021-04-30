package pl.fastus.wfrpg.hero.domain.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {

    private final SkillRepository repository;

    public List<Skill> getAllSkills() {
        return repository.findAll();
    }

    public Skill findSkillByName(String name) {
        return repository.findByName(name);
    }
}
