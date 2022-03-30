package edu.wctc.squirrels.controller;

import edu.wctc.squirrels.entity.Sighting;
import edu.wctc.squirrels.entity.Squirrel;
import edu.wctc.squirrels.service.LocationService;
import edu.wctc.squirrels.service.SightingService;
import edu.wctc.squirrels.service.SquirrelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SquirrelController {
    private SquirrelService squirrelService;
    private SightingService sightingService;

    @Autowired
    public SquirrelController(SquirrelService sqs, SightingService sis) {
        this.squirrelService = sqs;
        this.sightingService = sis;
    }

    @RequestMapping("/rankings")
    public String showRankings(Model model, @RequestParam("id") int squirrelId) {
        model.addAttribute("pageTitle", "Update Squirrel Rankings");
        model.addAttribute("squirrel", squirrelService.getSquirrel(squirrelId));

        return "rankings-form";
    }

    @PostMapping("/update-squirrel")
    public String processUpdate(Model model, @Valid @ModelAttribute Squirrel squirrel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            model.addAttribute("pageTitle", "Update Squirrel Rankings");
            model.addAttribute("squirrel", squirrelService.getSquirrel(squirrel.getId()));

            return "sighting-form";
        }

        squirrelService.updateSquirrel(squirrel);

        model.addAttribute("pageTitle", "Thank You!");
        model.addAttribute("squirrel", squirrelService.getSquirrel(squirrel.getId()));
        model.addAttribute("sightingList", sightingService.getSightingsForSquirrel(squirrel.getId()));

        return "confirmation";
    }
}
