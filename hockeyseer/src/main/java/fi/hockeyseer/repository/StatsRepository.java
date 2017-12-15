package fi.hockeyseer.repository;

import fi.hockeyseer.domain.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by LickiLicki on 03-Jun-17.
 */
public interface StatsRepository extends JpaRepository<Stats, Long>{
}
