package fi.hockeyseer.web;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import fi.hockeyseer.domain.Game;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.ResultService;
import fi.hockeyseer.service.SearchToolService;
import fi.hockeyseer.web.forms.SearchToolForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by LickiLicki on 06-Jul-17.
 */
@Controller
public class SearchToolController {

    private final SearchToolService searchToolService;

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public SearchToolController(SearchToolService searchToolService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository)
    {
        this.searchToolService = searchToolService;
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

        List<Game> games = searchToolService.resolveSearchedGames(searchToolForm);

        model.addAttribute("games", games);

        return "searchToolResult";
    }
}
