package pl.fastus.wfrpg.hero.domain.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/skills")
@RequiredArgsConstructor
@RestController
public class SkillController {

    private final SkillService service;

    @GetMapping
    public List<Skill> getAllSkills(){
        return service.getAllSkills();
    }

    @GetMapping("/{name}")
    public Skill getBySkillName(@PathVariable String name){
        return service.findSkillByName(name);
    }
}
