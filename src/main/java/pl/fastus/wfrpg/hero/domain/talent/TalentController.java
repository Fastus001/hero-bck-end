package pl.fastus.wfrpg.hero.domain.talent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/talents")
@RequiredArgsConstructor
@RestController
public class TalentController {

    private final TalentService service;

    @GetMapping
    List<Talent> getAllTalents(){
        return service.getAllTalents();
    }


}
