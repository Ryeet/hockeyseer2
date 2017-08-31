package fi.hockeyseer.web;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.ResultService;
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


    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public SearchToolController(GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository)
    {
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
        model.addAttribute("searchToolForm", new SearchToolForm());

        long againstSelect = searchToolForm.getAgainstSelect();
        switch ((int) againstSelect)
        {
            case 1:
                model.addAttribute("games", gameRepository.getGamesForTeamByAgainstTeam(searchToolForm.getTeam(), searchToolForm.getAgainstTeam()));
                break;
            case 2:
                model.addAttribute("games", gameRepository.getGamesForTeamByAgainstDivision(searchToolForm.getTeam(), searchToolForm.getAgainstDivision()));
                break;
            case 3:
                model.addAttribute("games", gameRepository.getGamesForTeamByAgainstConference(searchToolForm.getTeam(), searchToolForm.getAgainstConference()));
                break;
            case 4:
                model.addAttribute("games", gameRepository.getGamesForTeamByAgainstLeague(searchToolForm.getTeam()));
                break;
        }

        return "searchToolResult";
    }
}
