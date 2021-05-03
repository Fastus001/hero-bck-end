package pl.fastus.wfrpg.hero.domain.profession;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tom - 03.05.2021
 */
@RequestMapping("/professions")
@AllArgsConstructor
@RestController
public class ProfessionController {

    private final ProfessionService professionService;

    @GetMapping
    public List<Profession> getAllProfessions(){
        return professionService.getProfessions();
    }

    @GetMapping("/{lvl}")
    public List<Profession> getProfessionsByLvl(@PathVariable Integer lvl){
        return professionService.getByLvl(lvl);
    }


}
