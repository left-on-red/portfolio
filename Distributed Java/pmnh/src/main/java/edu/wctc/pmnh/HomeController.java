package edu.wctc.pmnh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/forward-me")
    public String doForward() {
        return "forward:/fossils/facts";
    }

    @RequestMapping("/redirect-me")
    public String doRedirect() {
        return "redirect:http://www.wctc.edu";
//        return "redirect:/glaciers/trail";
    }

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/view-cart")
    public String doCart() { return "pages/cart"; }

    @RequestMapping("product/detail")
    public String doProductDetail() { return "pages/product-detail.html"; }

    @PostMapping("/place-order")
    public String doPlaceOrder() { return "redirect:/"; }

    @RequestMapping("/search")
    public String doSearch() { return "redirect:https://www.google.com"; }
}
