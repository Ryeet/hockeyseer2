package fi.hockeyseer.web;

import fi.hockeyseer.web.forms.SearchToolForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {



    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("index", "test");


        return "index";
    }
}
