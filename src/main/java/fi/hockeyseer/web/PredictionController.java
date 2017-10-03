package fi.hockeyseer.web;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Team;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.CalculatedStatsService;
import fi.hockeyseer.service.ResultService;
import fi.hockeyseer.service.TeamStatsService;
import fi.hockeyseer.web.forms.SearchToolForm;
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

    private final TeamStatsService teamStatsService;

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public PredictionController(TeamStatsService teamStatsService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository)
    {
        this.teamStatsService = teamStatsService;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
    }

    @GetMapping(value = "/prediction", params = {"home", "visitor"})
    public String getPrediction(Model model,
                                @RequestParam(value = "home") Long homeId,
                                @RequestParam(value = "visitor") Long visitorId)
    {
        List<Team> teams = Arrays.asList(teamRepository.getOne(homeId), teamRepository.getOne((visitorId)));
        List<String> seasons = Arrays.asList("20162017");

        teamStatsService.generateAllStats(teams, seasons);

        return "searchTool";
    }
}
