package pl.fastus.wfrpg.hero.domain.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SkillService {

    private final SkillRepository repository;
}
