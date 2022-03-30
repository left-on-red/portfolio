package edu.wctc.pmnh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/glaciers")
public class GlacierController {
    @RequestMapping("/facts")
    public String showGlacierFacts() {
        return "pages/glacier-facts";
    }

    @RequestMapping("/trail")
    public String showTrailFacts() {
        return "pages/ice-age-trail";
    }
}
