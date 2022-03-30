package edu.wctc.datajpa.controller;

import edu.wctc.datajpa.entity.CatalogItem;
import edu.wctc.datajpa.service.CatalogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogController {
    private CatalogItemService catalogItemService;

    @Autowired
    public CatalogController(CatalogItemService service) { this.catalogItemService = service; }

    @RequestMapping("/catalog")
    public String getCatalog(Model model) {
        model.addAttribute("pageTitle", "Shop Catalog");
        model.addAttribute("itemList", catalogItemService.getItems());

        return "catalog";
    }

    @RequestMapping("/order")
    public String addOrder(Model model, @RequestParam("id") int itemId) {
        CatalogItem item = catalogItemService.getItem(itemId);
        model.addAttribute("itemName", item.getName());

        if (item.getStock() > 0) {
            item.setStock(item.getStock() - 1);
            catalogItemService.updateItem(item);

            model.addAttribute("pageTitle", "Thanks");
            return "thank_you";
        }

        else {
            model.addAttribute("pageTitle", "Out of Stock");
            return "out_of_stock";
        }
    }
}
