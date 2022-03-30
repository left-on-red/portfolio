package edu.wctc.stockpurchase.repo;

import edu.wctc.stockpurchase.entity.StockPurchase;
import org.springframework.data.repository.CrudRepository;

public interface StockPurchaseRepository extends CrudRepository<StockPurchase, Integer> {

}
