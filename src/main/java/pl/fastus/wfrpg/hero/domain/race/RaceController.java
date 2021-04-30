package pl.fastus.wfrpg.hero.domain.race;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/races")
@RestController
public class RaceController {

    private final RaceService service;

    @GetMapping
    public List<Race> getAllRaces(){
        return service.getAllRaces();
    }
}
