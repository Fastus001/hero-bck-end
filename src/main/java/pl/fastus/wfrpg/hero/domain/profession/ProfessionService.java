package pl.fastus.wfrpg.hero.domain.profession;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Tom - 03.05.2021
 */
@AllArgsConstructor
@Service
public class ProfessionService {

    private final ProfessionRepository repository;
}
