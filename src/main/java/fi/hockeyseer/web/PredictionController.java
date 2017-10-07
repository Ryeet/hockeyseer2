package fi.hockeyseer.web;

import fi.hockeyseer.domain.AdjustedPowerForOlli;
import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Team;
import fi.hockeyseer.repository.AdjustedPowerForOlliRepository;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.CalculatedStatsService;
import fi.hockeyseer.service.ResultService;
import fi.hockeyseer.service.TeamStatsService;
import fi.hockeyseer.service.data.stats.team.TeamStats;
import fi.hockeyseer.web.forms.SearchToolForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LickiLicki on 03-Oct-17.
 */
@Controller
public class PredictionController {

    private static Logger log = LoggerFactory.getLogger(PredictionController.class);

    private final TeamStatsService teamStatsService;

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    private final AdjustedPowerForOlliRepository adjustedPowerForOlliRepository;

    @Autowired
    public PredictionController(TeamStatsService teamStatsService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository, AdjustedPowerForOlliRepository adjustedPowerForOlliRepository)
    {
        this.teamStatsService = teamStatsService;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
        this.adjustedPowerForOlliRepository = adjustedPowerForOlliRepository;
    }

    @GetMapping(value = "/prediction", params = {"home", "visitor"})
    public String getPrediction(Model model,
                                @RequestParam(value = "home") Long homeId,
                                @RequestParam(value = "visitor") Long visitorId)
    {
        List<Team> teams = Arrays.asList(teamRepository.getOne(homeId), teamRepository.getOne((visitorId)));
        List<String> seasons = Arrays.asList("20162017");

        List<TeamStats> teamsStatsBase = teamStatsService.calculateTeamStatsBase(seasons, null);
        List<TeamStats> finalTeamStats = teamStatsService.calculateFinalTeamStats(teams, teamsStatsBase);


        return "prediction";
    }

//    @GetMapping(value = "/leagueavgs")
//    public String getLeagueAvgsLastYear(Model model)
//    {
//        List<Game> games = gameRepository.getGamesBySeasonAndRealTeams("20162017");
//
//        for (Game game : games)
//        {
//            if (game.getId() > 610)
//            {
//                List<String> seasons = Arrays.asList("20162017");
//                teamStatsService.calculateTeamStatsBase(seasons, game.getDate());
//            }
//        }
//        return "searchTool";
//    }



//    @GetMapping(value = "/predictions")
//    public String getPredictionLastYear(Model model)
//    {
//        List<Game> games = gameRepository.getGamesBySeasonAndRealTeams("20162017");
//
//
//        for (Game game : games)
//        {
//            if (game.getId() > 668)
//            {
//                List<Team> teams = Arrays.asList(game.getHomeTeam(), game.getVisitorTeam());
//                List<String> seasons = Arrays.asList("20162017");
//
//                List<TeamStats> teamsStatsBase = teamStatsService.calculateTeamStatsBase(seasons, game.getDate());
//                List<TeamStats> finalTeamStats = teamStatsService.calculateFinalTeamStats(teams, teamsStatsBase);
//
//                for (TeamStats stats : finalTeamStats)
//                {
//                    AdjustedPowerForOlli adjustedPowerForOlli = new AdjustedPowerForOlli();
//                    adjustedPowerForOlli.setGameId(game.getId());
//
//                    if (stats.getTeam().getId() == game.getHomeTeam().getId())
//                    {
//                        adjustedPowerForOlli.setTeamId(game.getHomeTeam().getId());
//                        adjustedPowerForOlli.setAdjustedWinTendency(stats.getAdjustedPowersHome().getAdjustedWinTendency());
//                        adjustedPowerForOlli.setAdjustedLossTendency(stats.getAdjustedPowersHome().getAdjustedLossTendency());
//                        adjustedPowerForOlli.setAdjustedTieTendency(stats.getAdjustedPowersHome().getAdjustedTieTendency());
//                        adjustedPowerForOlli.setAdjustedScorePower(stats.getAdjustedPowersHome().getAdjustedScorePower());
//                        adjustedPowerForOlli.setAdjustedDefenseSuck(stats.getAdjustedPowersHome().getAdjustedDefenseSuck());
//                    }
//                    else
//                    {
//                        adjustedPowerForOlli.setTeamId(game.getVisitorTeam().getId());
//                        adjustedPowerForOlli.setAdjustedWinTendency(stats.getAdjustedPowersVisitor().getAdjustedWinTendency());
//                        adjustedPowerForOlli.setAdjustedLossTendency(stats.getAdjustedPowersVisitor().getAdjustedLossTendency());
//                        adjustedPowerForOlli.setAdjustedTieTendency(stats.getAdjustedPowersVisitor().getAdjustedTieTendency());
//                        adjustedPowerForOlli.setAdjustedScorePower(stats.getAdjustedPowersVisitor().getAdjustedScorePower());
//                        adjustedPowerForOlli.setAdjustedDefenseSuck(stats.getAdjustedPowersVisitor().getAdjustedDefenseSuck());
//                    }
//
//                    log.debug("Game ::: " + adjustedPowerForOlli.getGameId());
//                    log.debug("Team ::: " + adjustedPowerForOlli.getTeamId());
//
//                    adjustedPowerForOlliRepository.save(adjustedPowerForOlli);
//                }
//            }
//        }
//        return "searchTool";
//    }
}
