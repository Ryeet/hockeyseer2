package fi.hockeyseer.web;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Team;
import fi.hockeyseer.domain.enumeration.Season;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.calc.TeamStatsService;
import fi.hockeyseer.service.calc.stats.team.TeamStats;
import fi.hockeyseer.service.shared.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController
{

    private static Logger log = LoggerFactory.getLogger(PredictionController.class);

    private final TeamStatsService teamStatsService;

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public AdminController(TeamStatsService teamStatsService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository)
    {
        this.teamStatsService = teamStatsService;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
    }

    @GetMapping(value = "seasonStats")
    public String showSeasonStats(Model model, @RequestParam(value = "season") String season)
    {
        List<TeamStats> teamsStats = teamStatsService.calculateTeamStatsBase(Arrays.asList(season), LocalDateTime.now());

        model.addAttribute("teamsStats", teamsStats);

        return "seasonStats";
    }
}
