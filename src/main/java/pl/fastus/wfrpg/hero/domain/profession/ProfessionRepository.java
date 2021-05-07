package pl.fastus.wfrpg.hero.domain.profession;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Tom - 03.05.2021
 */
public interface ProfessionRepository extends JpaRepository<Profession, Long> {

    List<Profession> findAllByLevel(Integer lvl);

    List<Profession> findAllByLevelAndMaleAndAvailableForRacesLike(String lvl, Boolean male, String name);
}
