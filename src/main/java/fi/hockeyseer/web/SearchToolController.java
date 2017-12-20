package fi.hockeyseer.web;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.shared.ResultService;
import fi.hockeyseer.service.calc.CalculatedStatsService;
import fi.hockeyseer.web.forms.SearchToolForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by LickiLicki on 06-Jul-17.
 */
@Controller
public class SearchToolController {

    private final CalculatedStatsService calculatedStatsService;

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public SearchToolController(CalculatedStatsService calculatedStatsService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository)
    {
        this.calculatedStatsService = calculatedStatsService;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
    }

    @GetMapping("/searchtool")
    public String getSearchTool(Model model)
    {
        model.addAttribute("teams", teamRepository.findAllByIdLessThan100ByOrderByNameAsc());
        model.addAttribute("searchToolForm", new SearchToolForm());

        return "searchTool";
    }

    @PostMapping("/searchtool")
    public String postSearchTool(Model model, @ModelAttribute SearchToolForm searchToolForm)
    {
        model.addAttribute("teams", teamRepository.findAllByIdLessThan100ByOrderByNameAsc());
        model.addAttribute("searchToolForm", searchToolForm);

        List<Game> games = calculatedStatsService.resolveSearchedGames(searchToolForm);
        model.addAttribute("games", games);

        model.addAttribute("searchToolStats", calculatedStatsService.calculateWTLandMargins(games, searchToolForm.getTeam()));

        model.addAttribute("resultDistribution", calculatedStatsService.countResultDistiribution(games, searchToolForm.getTeam()));

        return "searchToolResult";
    }
}
