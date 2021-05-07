package pl.fastus.wfrpg.hero.domain.profession;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tom - 03.05.2021
 */
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
@RequestMapping("/api/professions")
@AllArgsConstructor
@RestController
@Validated
public class ProfessionController {

    private final ProfessionService professionService;

    @GetMapping
    public List<Profession> getAllProfessions(){
        return professionService.getProfessions();
    }

    @GetMapping("/{lvl}")
    public List<Profession> getProfessionsByLvl(@PathVariable @Range(min = 1,max = 4) Integer lvl){
        return professionService.getByLvl(lvl);
    }

    @GetMapping("/{lvl}/{sex}/{race}")
    public List<Profession> getProfessionsByLvlAndSex(@PathVariable @Range(min = 1,max = 4) Integer lvl,
                                                      @PathVariable boolean sex, @PathVariable String race){
        return professionService.getByLvlAndSex(String.valueOf(lvl), sex, race);
    }


}


