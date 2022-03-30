package edu.wctc.pmnh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fossils")
public class FossilController {
    @RequestMapping("/trilobites")
    public String showTriloFacts() {
        return "pages/trilobites";
    }

    @RequestMapping("/facts")
    public String showFossilFacts() {
        return "pages/fossil-facts";
    }
}
