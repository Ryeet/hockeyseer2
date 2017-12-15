package fi.hockeyseer.repository;

import fi.hockeyseer.domain.Team;
import fi.hockeyseer.domain.enumeration.Conference;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by alekstu on 31.5.2017.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByConference(Conference conference);

    @Query(value = "SELECT * FROM team WHERE id < 100 ORDER BY name ASC", nativeQuery = true)
    List<Team> findAllByIdLessThan100ByOrderByNameAsc();

    Team findByName(String name);

//    @Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
//    User findByLastnameOrFirstname(@Param("lastname") String lastname,
//                                   @Param("firstname") String firstname);
}
