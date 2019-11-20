package fi.hockeyseer.repository;

import fi.hockeyseer.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by LickiLicki on 03-Jun-17.
 */
public interface GameRepository extends JpaRepository<Game,Long>{

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN :againstTeam AND season IN :season AND played = :played) " +
            "OR (homeTeam_id IN :againstTeam AND visitorTeam_id = :team AND season IN :season AND played = :played) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstTeam(@Param("team") Long team,
                                            @Param("againstTeam")List<String> againstTeam,
                                            @Param("season")List<String> season,
                                            @Param("played")boolean played);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN (SELECT id FROM team WHERE division IN :againstDivision) AND season IN :season AND played = :played) " +
            "OR (homeTeam_id IN (SELECT id FROM team WHERE division IN :againstDivision) AND visitorTeam_id = :team AND season IN :season AND played = :played) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstDivision(@Param("team") Long team,
                                                @Param("againstDivision")List<String> againstDivision,
                                                @Param("season")List<String> season,
                                                @Param("played")boolean played);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND visitorTeam_id IN (SELECT id FROM team WHERE conference IN :againstConference) AND season IN :season AND played = :played) " +
            "OR (homeTeam_id IN (SELECT id FROM team WHERE conference IN :againstConference) AND visitorTeam_id = :team AND season IN :season AND played = :played) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstConference(@Param("team") Long team,
                                                  @Param("againstConference")List<String> againstConference,
                                                  @Param("season")List<String> season,
                                                  @Param("played")boolean played);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND season IN :season AND played = :played) OR (visitorTeam_id = :team AND season IN :season AND played = :played) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstLeague(@Param("team") Long team,
                                              @Param("season")List<String> season,
                                              @Param("played")boolean played);

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND season IN :season AND played = :played AND date < :date) OR (visitorTeam_id = :team AND season IN :season AND played = :played AND date < :date) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstLeagueWithDate(@Param("team") Long team,
                                                      @Param("season") List<String> season,
                                                      @Param("played") boolean played,
                                                      @Param("date") LocalDateTime date);

    @Query(value = "SELECT * FROM game WHERE date >= :date LIMIT 50", nativeQuery = true)
    List<Game> find50NextUpComingGames(@Param("date") LocalDateTime date);

    @Query(value = "SELECT * FROM game WHERE played = :played order by date desc limit 50", nativeQuery = true)
    List<Game> find50LatestPlayedGames(@Param("played") boolean played);

    List<Game> findBySeason(String season);
    
    boolean existsBySeason(final String season);
}
