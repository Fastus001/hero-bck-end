package pl.fastus.wfrpg.hero.domain.talent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TalentService {

    private final TalentRepository talentRepository;

    public List<Talent> getAllTalents() {
        return talentRepository.findAll();
    }
}
