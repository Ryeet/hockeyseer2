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

    @Query(value = "SELECT * FROM game WHERE (homeTeam_id = :team AND season IN :season AND played = :played) OR (visitorTeam_id = :team AND season IN :season AND played = :played) ORDER BY date ASC", nativeQuery = true)
    List<Game> getGamesForTeamByAgainstLeagueWithDate(@Param("team") Long team,
                                                      @Param("season")List<String> season,
                                                      @Param("played")boolean played);


    @Query(value = "SELECT * FROM game WHERE date >= CURDATE() AND played = FALSE LIMIT 1", nativeQuery = true)
    Optional<Game> findNextUpComingGame();

    @Query(value = "SELECT * FROM game WHERE date >= :date LIMIT 20", nativeQuery = true)
    List<Game> find20NextUpComingGames(@Param("date") LocalDateTime date);

    List<Game> findByDate(LocalDate date);

    List<Game> findByDateAndPlayedIsFalse(LocalDate date);

    List<Game> findBySeason(String season);


    @Query(value = "SELECT * FROM game WHERE homeTeam_id < 100 AND season = :season ", nativeQuery = true)
    List<Game> getGamesBySeasonAndRealTeams(@Param("season") String season);
}
