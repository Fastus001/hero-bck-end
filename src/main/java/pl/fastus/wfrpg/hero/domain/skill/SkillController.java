package pl.fastus.wfrpg.hero.domain.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/skills")
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

    @CrossOrigin(allowedHeaders = "*", originPatterns = "*")
    @GetMapping("/list")
    public void getSkillListFromNames(@RequestBody List<String> names){
        System.out.println(names);
        //TODO - method in service layer!!!
    }

}
