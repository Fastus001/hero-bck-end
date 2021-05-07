package pl.fastus.wfrpg.hero.domain.race;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RaceRepository extends JpaRepository<Race, Long> {

    @Query("select r.name from Race r")
    List<String> getAllRaceName();
}
