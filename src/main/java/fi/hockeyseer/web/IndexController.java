package fi.hockeyseer.web;

import fi.hockeyseer.service.shared.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    private final GameService gameService;

    public IndexController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("index", "test");
        model.addAttribute("games", gameService.findUpComingGames());

        return "index";
    }
}
