package fi.hockeyseer.repository;

import fi.hockeyseer.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LickiLicki on 03-Jun-17.
 */
public interface GameRepository extends JpaRepository<Game,Long>{

//    @Query("SELECT * FROM game WHERE homeTeam_id = :team OR visitorTeam_id = :team AND homeTeam_id IN :againstTeam OR visitorTeam_id IN :againstTeam ORDER BY date DESC")
//    Game getGamesForTeamByAgainstTeam(@Param("team") String team,
//                                      @Param("againstTeam")List<String> againstTeam);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN :againstTeam) OR (homeTeam_id IN :againstTeam AND visitorTeam_id = :team) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstTeam(@Param("team") Long team,
                                            @Param("againstTeam")List<String> againstTeam);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN (SELECT id FROM team WHERE division IN :againstDivision)) " +
                    "OR (homeTeam_id IN (SELECT id FROM team WHERE division IN :againstDivision) AND visitorTeam_id = :team) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstDivision(@Param("team") Long team,
                                                @Param("againstDivision")List<String> againstDivision);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN (SELECT id FROM team WHERE conference IN :againstConference)) " +
            "OR (homeTeam_id IN (SELECT id FROM team WHERE conference IN :againstConference) AND visitorTeam_id = :team) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstConference(@Param("team") Long team,
                                                  @Param("againstConference")List<String> againstConference);

    @Query(value = "SELECT * FROM game WHERE homeTeam_id = :team OR visitorTeam_id = :team ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstLeague(@Param("team") Long team);



    List<Game> findBySeason(String season);
}
