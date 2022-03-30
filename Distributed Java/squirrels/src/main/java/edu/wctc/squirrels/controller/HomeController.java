package edu.wctc.squirrels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("pageTitle", "24/7 SquirrelWatch");
        return "index";
    }

    @RequestMapping("/credits")
    public String showCreditsPage(Model model) {
        model.addAttribute("pageTitle", "Credits");
        return "credits";
    }
}
