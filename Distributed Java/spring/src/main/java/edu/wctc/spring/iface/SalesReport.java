package edu.wctc.spring.iface;

import edu.wctc.spring.Sale;
import java.util.List;

public interface SalesReport {
    String generateReport(List<Sale> salesList);
}
