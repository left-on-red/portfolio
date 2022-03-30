package edu.wctc.stockpurchase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import edu.wctc.stockpurchase.entity.StockPurchase;
import edu.wctc.stockpurchase.repo.StockPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockPurchaseService {
    private StockPurchaseRepository repo;
    private ObjectMapper objectMapper;

    @Autowired
    public StockPurchaseService(StockPurchaseRepository spr, ObjectMapper om) {
        this.repo = spr;
        this.objectMapper = om;
    }

    public List<StockPurchase> getStockPurchases() {
        return (List<StockPurchase>) repo.findAll();
    }

    public StockPurchase getStockPurchaseById(int id) {
        Optional<StockPurchase> purchase = repo.findById(id);
        if (purchase.isPresent()) { return purchase.get(); }
        else { return null; }
    }

    public StockPurchase addStockPurchase(StockPurchase purchase) {
        return repo.save(purchase);
    }

    public StockPurchase updateStockPurchase(StockPurchase new_purchase) {
        int id = new_purchase.getId();

        return repo.findById(id).map(purchase -> {
            purchase.setSymbol(new_purchase.getSymbol());
            purchase.setShares(new_purchase.getShares());
            purchase.setPricePerShare(new_purchase.getPricePerShare());
            purchase.setPurchaseDate(new_purchase.getPurchaseDate());
            return repo.save(new_purchase);
        }).orElseGet(() -> {
           new_purchase.setId(id);
           return repo.save(new_purchase);
        });
    }

    public void deleteStockPurchase(int id) {
        Optional<StockPurchase> purchase = repo.findById(id);
        if (purchase.isPresent()) { repo.delete(purchase.get()); }
    }

    public StockPurchase patchStockPurchase(JsonPatch patch, StockPurchase purchase) throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(purchase, JsonNode.class));
        StockPurchase new_purchase = objectMapper.treeToValue(patched, StockPurchase.class);
        return this.updateStockPurchase(new_purchase);
    }
}
