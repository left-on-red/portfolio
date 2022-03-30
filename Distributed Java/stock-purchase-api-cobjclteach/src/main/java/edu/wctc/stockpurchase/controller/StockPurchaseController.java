package edu.wctc.stockpurchase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import edu.wctc.stockpurchase.entity.StockPurchase;
import edu.wctc.stockpurchase.service.StockPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockpurchases")
public class StockPurchaseController {

    private StockPurchaseService service;

    @Autowired
    public StockPurchaseController(StockPurchaseService sps) {
        this.service = sps;
    }

    @GetMapping("")
    List<StockPurchase> getStockPurchases() {
        return service.getStockPurchases();
    }

    @GetMapping("{id}")
    StockPurchase getStockPurchase(@PathVariable int id) {
        return service.getStockPurchaseById(id);
    }

    @PostMapping("")
    StockPurchase addStockPurchase(@RequestBody StockPurchase purchase) {
        return service.addStockPurchase(purchase);
    }

    @PutMapping("")
    StockPurchase putStockPurchase(@RequestBody StockPurchase purchase) {
        return service.updateStockPurchase(purchase);
    }

    @DeleteMapping("{id}")
    void deleteStockPurchase(@PathVariable int id) {
        service.deleteStockPurchase(id);
    }

    @PatchMapping("{id}")
    public StockPurchase patchStockPurchase(@PathVariable int id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        return service.patchStockPurchase(patch, service.getStockPurchaseById(id));
    }
}
