package fi.hockeyseer.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by alekstu on 21.6.2017.
 */

@Controller
public class GreetingsController {


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
