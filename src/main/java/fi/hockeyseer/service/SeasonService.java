package fi.hockeyseer.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Result;
import fi.hockeyseer.domain.Stats;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.ResultRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.json.JsonSeason;
import fi.hockeyseer.utility.ConnUtil;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;


/**
 * Created by alekstu on 5.6.2017.
 */

@Service
public class SeasonService {

    private static Logger log = LoggerFactory.getLogger(SeasonService.class);

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public SeasonService(GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
    }

    public void addFullSeasonResults(String seasonUrl) throws IOException {

        String json = ConnUtil.getResponseBody(
                "https://statsapi.web.nhl.com/api/v1/schedule?"
                    + seasonUrl
                    + "&expand=schedule.linescore");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonSeason season = mapper.readValue(json, JsonSeason.class);
        log.debug("season size =" + season.getJsonGameDates().size());
// Todo: fix "special games" - team pacific etc
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MMM-dd");
        season.getJsonGameDates()
                .stream()
                .forEach(dates ->
                        dates.getJsonGames()
                                .stream()
                                .forEach(date -> {
                                    Game game = new Game();
                                    ZonedDateTime d = ZonedDateTime.parse(date.getGameDate());
                                    game.setDate(d.toLocalDate());
                                    game.setHomeTeam(teamRepository.findByName(date.getJsonGameScore().getTeams().getHome().getTeam().getName()));
                                    game.setVisitorTeam(teamRepository.findByName(date.getJsonGameScore().getTeams().getAway().getTeam().getName()));
                                    game.setSeason(date.getSeason());
                                    game.setPlayed(true);
                                    game.setResult(resultService.getGameResult(date.getJsonGameScore()));
                                    game.setWinner(determineWinner(game.getResult().getHome_total(), game.getResult().getVisitor_total()));
                                    log.debug("saving game = " + game.toString());
                                    gameRepository.save(game);
                                })
                );



    }

    private Integer determineWinner(Integer home_total, Integer visitor_total) {
        if (home_total == visitor_total) return 0;
        return (home_total > visitor_total) ?  1 : 2;
    }
}
