package pl.fastus.wfrpg.hero.domain.race;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
@RequiredArgsConstructor
@RequestMapping("/api/races")
@RestController
public class RaceController {

    private final RaceService service;

    @GetMapping
    public List<Race> getAllRaces(){
        return service.getAllRaces();
    }
}
