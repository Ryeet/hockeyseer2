package fi.hockeyseer.repository;

import fi.hockeyseer.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by LickiLicki on 03-Jun-17.
 */
public interface GameRepository extends JpaRepository<Game,Long>{

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN :againstTeam AND season IN :season) OR (homeTeam_id IN :againstTeam AND visitorTeam_id = :team AND season IN :season) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstTeam(@Param("team") Long team,
                                            @Param("againstTeam")List<String> againstTeam,
                                            @Param("season")List<String> season);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN (SELECT id FROM team WHERE division IN :againstDivision)) " +
                    "OR (homeTeam_id IN (SELECT id FROM team WHERE division IN :againstDivision) AND visitorTeam_id = :team) AND season IN :season ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstDivision(@Param("team") Long team,
                                                @Param("againstDivision")List<String> againstDivision,
                                                @Param("season")List<String> season);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN (SELECT id FROM team WHERE conference IN :againstConference)) " +
                    "OR (homeTeam_id IN (SELECT id FROM team WHERE conference IN :againstConference) AND visitorTeam_id = :team) AND season IN :season ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstConference(@Param("team") Long team,
                                                  @Param("againstConference")List<String> againstConference,
                                                  @Param("season")List<String> season);

    @Query(value = "SELECT * FROM game WHERE homeTeam_id = :team OR visitorTeam_id = :team AND season IN :season ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstLeague(@Param("team") Long team,
                                              @Param("season")List<String> season);


    @Query(value = "SELECT * FROM game WHERE date >= CURDATE() AND played = FALSE LIMIT 1", nativeQuery = true)
    Game findNextUpComingGame();

    List<Game> findByDateAndPlayedIsFalse(LocalDate date);

    List<Game> findBySeason(String season);
}
