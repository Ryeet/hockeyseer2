package fi.hockeyseer.service.dataimport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.hockeyseer.constants.SeasonUrl;
import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Result;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.shared.ResultService;
import fi.hockeyseer.service.dataimport.json.JsonGame;
import fi.hockeyseer.service.dataimport.json.JsonGameDate;
import fi.hockeyseer.service.dataimport.json.JsonGameScore;
import fi.hockeyseer.service.dataimport.json.JsonSeason;
import fi.hockeyseer.utility.ConnUtil;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;


/**
 * Created by alekstu on 5.6.2017.
 */

@Service
public class ImportService {

    private static Logger log = LoggerFactory.getLogger(ImportService.class);

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public ImportService(GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
    }


    public void importSeasons(List<String> seasons) {
        seasons.forEach(s -> {
            try {
                importSingleSeason(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void importSingleSeason(String url) throws IOException {


        if (!seasonIsInDatabase(url)) {

            String json = ConnUtil.getResponseBody(
                    "https://statsapi.web.nhl.com/api/v1/schedule?"
                            + url
                            + "&expand=schedule.linescore");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonSeason season = mapper.readValue(json, JsonSeason.class);
            Integer totalGames = getSeasonGameAmount(season);
            final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MMM-dd");

            season.getJsonGameDates()
                    .stream()
                    .map(dates -> dates.getJsonGames())
                    .flatMap(games -> games.stream())
                    .forEach(game -> {
                        Game newGame =
                                new Game()
                                        .date(ZonedDateTime.parse(game.getGameDate()).toLocalDateTime().plusHours(2))
                                        .homeTeam(teamRepository.findByName(game.getJsonGameScore().getTeams().getHome().getTeam().getName()))
                                        .visitorTeam(teamRepository.findByName(game.getJsonGameScore().getTeams().getAway().getTeam().getName()))
                                        .season(game.getSeason())
                                        .played(false);

                        if (game.getJsonGameScore().getJsonGamePeriods().size() != 0) {

                            newGame.played(true)
                                    .result(getGameResult(game.getJsonGameScore()))
                                    .winner(determineWinner(newGame.getResult().getHome_total(), newGame.getResult().getVisitor_total()));
                        }
                        Game saved = gameRepository.save(newGame);
                        log.debug("Game> " + saved.getId() + "/" + totalGames);
                    });
        }
    }



    private Result getGameResult(JsonGameScore jsonGameScore) {
        Result result = new Result();
        jsonGameScore.getJsonGamePeriods()
                .stream()
                .forEach(period -> {

                    switch (period.getNum()) {
                        case 1:
                            result.setHome_1st(period.getHome().getGoals());
                            result.setVisitor_1st(period.getAway().getGoals());
                        case 2:
                            result.setHome_2nd(period.getHome().getGoals());
                            result.setVisitor_2nd(period.getAway().getGoals());
                        case 3:
                            result.setHome_3rd(period.getHome().getGoals());
                            result.setVisitor_3rd(period.getAway().getGoals());

                    }
                    result.setHome_total(result.getHome_1st() + result.getHome_2nd() + result.getHome_3rd());
                    result.setVisitor_total(result.getVisitor_1st() + result.getVisitor_2nd() + result.getVisitor_3rd());

                });
        result.setHome_shots(jsonGameScore.getTeams().getHome().getShotsOnGoal());
        result.setVisitor_shots(jsonGameScore.getTeams().getAway().getShotsOnGoal());
        Result saved = resultService.save(result);
        return saved;
    }


    private Integer determineWinner(Integer home_total, Integer visitor_total) {
        if (home_total == visitor_total) return 0;
        return (home_total > visitor_total) ? 1 : 2;
    }

    private Integer getSeasonGameAmount(JsonSeason season) {
        Integer count = 0;
        for (JsonGameDate date : season.getJsonGameDates()) {
            for (JsonGame game : date.getJsonGames()) {
                count++;
            }
        }
        Long repSize = gameRepository.count();
        Integer i = repSize != null ? repSize.intValue() : null;
        return count + i;

    }

    private Boolean seasonIsInDatabase(String url) {

        switch (url.toString()) {

            case SeasonUrl.S2018_2019:
                return !gameRepository.findBySeason("20182019").isEmpty();
            case SeasonUrl.S2017_2018:
                return !gameRepository.findBySeason("20172018").isEmpty();
            case SeasonUrl.S2016_2017:
                return !gameRepository.findBySeason("20162017").isEmpty();
            case SeasonUrl.S2015_2016:
                return !gameRepository.findBySeason("20152016").isEmpty();
            case SeasonUrl.S2014_2015:
                return !gameRepository.findBySeason("20142015").isEmpty();
            case SeasonUrl.S2013_2014:
                return !gameRepository.findBySeason("20132014").isEmpty();
        }


        return true;
    }

}
