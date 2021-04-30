package pl.fastus.wfrpg.hero.domain.race;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RaceService {

    private final RaceRepository repository;

    public List<Race> getAllRaces(){
        return repository.findAll();
    }
}
