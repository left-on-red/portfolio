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
public class SightingController {
    private SquirrelService squirrelService;
    private SightingService sightingService;
    private LocationService locationService;

    @Autowired
    public SightingController(SquirrelService sqs, SightingService sis, LocationService los) {
        this.squirrelService = sqs;
        this.sightingService = sis;
        this.locationService = los;
    }

    @ModelAttribute
    public void locationList(Model model) {
        model.addAttribute("locationList", locationService.getLocationList());
    }

    @PostMapping("/save")
    public String processSighting(Model model, @Valid @ModelAttribute Sighting sighting, BindingResult bindingResult) {
        int squirrelId = sighting.getSquirrelId();

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            model.addAttribute("pageTitle", "Report Sighting");
            model.addAttribute("squirrel", squirrelService.getSquirrel(squirrelId));
            return "sighting-form";
        }

        sightingService.saveSighting(sighting);

        model.addAttribute("pageTitle", "Thank You!");
        model.addAttribute("squirrel", squirrelService.getSquirrel(squirrelId));
        model.addAttribute("sightingList", sightingService.getSightingsForSquirrel(squirrelId));

        return "confirmation";
    }

    @RequestMapping("/sightings")
    public String showRecentSightings(Model model, @RequestParam("id") int squirrelId) {
        model.addAttribute("pageTitle", "Recent Sightings");
        model.addAttribute("squirrel", squirrelService.getSquirrel(squirrelId));
        model.addAttribute("sightingList", sightingService.getSightingsForSquirrel(squirrelId));
        return "sightings";
    }

    @RequestMapping("/report")
    public String showSightingForm(Model model, @RequestParam("id") int squirrelId) {
        model.addAttribute("pageTitle", "Report Sighting");
        model.addAttribute("squirrel", squirrelService.getSquirrel(squirrelId));

        Sighting si = new Sighting();
        si.setSquirrelId(squirrelId);
        model.addAttribute("sighting", si);

        return "sighting-form";
    }

    @RequestMapping("/list")
    public String showSquirrelList(Model model) {
        model.addAttribute("pageTitle", "Pick a Squirrel");
        model.addAttribute("squirrelList", squirrelService.getSquirrelList());

        return "squirrel-list";
    }
}
