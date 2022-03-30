package edu.wctc.datajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String redirectToCatalog() {
        return "redirect:/catalog";
    }
}
