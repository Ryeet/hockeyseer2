package fi.hockeyseer.web;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Team;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.calc.PredictionStatsService;
import fi.hockeyseer.service.calc.stats.basic.PredictionStats;
import fi.hockeyseer.service.shared.ResultService;
import fi.hockeyseer.service.calc.TeamStatsService;
import fi.hockeyseer.service.calc.stats.team.TeamStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private final PredictionStatsService predictionStatsService;

    @Autowired
    public PredictionController(TeamStatsService teamStatsService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository, PredictionStatsService predictionStatsService)
    {
        this.teamStatsService = teamStatsService;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
        this.predictionStatsService = predictionStatsService;
    }

    @GetMapping(value = "/prediction", params = {"game"})
    public String getPrediction(Model model, @RequestParam(value = "game") Long gameId)
    {
        Game game = gameRepository.getOne(gameId);
        List<String> seasons = Arrays.asList(game.getSeason());

        List<TeamStats> teamsStatsBase = teamStatsService.calculateTeamStatsBase(seasons, game.getDate());
        List<TeamStats> finalHomeTeamStats = teamStatsService.calculateFinalTeamStats(Arrays.asList(game.getHomeTeam()), teamsStatsBase);
        List<TeamStats> finalVisitorTeamStats = teamStatsService.calculateFinalTeamStats(Arrays.asList(game.getVisitorTeam()), teamsStatsBase);
        PredictionStats predictionStats = predictionStatsService.calculatePredictionStats(finalHomeTeamStats.get(0), finalVisitorTeamStats.get(0));

        model.addAttribute("predictionStats", predictionStats);
        model.addAttribute("finalHomeTeamStats", finalHomeTeamStats);
        model.addAttribute("finalVisitorTeamStats", finalVisitorTeamStats);
        model.addAttribute("played", game.getPlayed());
        model.addAttribute("home_total", game.getResult().getHome_total());
        model.addAttribute("visitor_total", game.getResult().getVisitor_total());

        return "prediction";
    }
}
