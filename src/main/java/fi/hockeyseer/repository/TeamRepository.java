package fi.hockeyseer.repository;

import fi.hockeyseer.domain.Team;
import fi.hockeyseer.domain.enumeration.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by alekstu on 31.5.2017.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByConference(Conference conference);

    Team findByName(String name);
}
