package pl.fastus.wfrpg.hero.domain.profession;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tom - 03.05.2021
 */
@AllArgsConstructor
@Service
public class ProfessionService {

    private final ProfessionRepository repository;


    public List<Profession> getProfessions(){
        return repository.findAll();
    }

    public List<Profession> getByLvl(Integer lvl) {
        return repository.findAllByLevel(lvl);
    }

    public List<Profession> getByLvlAndSex(String lvl, Boolean sex) {
        return repository.findAllByLevelAndMale(lvl, sex);
    }
}
