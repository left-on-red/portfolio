package edu.wctc.spring.impl.report;

import edu.wctc.spring.Sale;
import edu.wctc.spring.iface.SalesReport;

import java.util.ArrayList;
import java.util.List;

public class SalesDetailReport implements SalesReport {
    @Override
    public String generateReport(List<Sale> salesList) {
        String format = "%20s%20s%10s%10s%10s";
        List<String> lines = new ArrayList<>();

        lines.add("\nSALES DETAIL REPORT");
        lines.add("----------------------------------------------------------------------");
        lines.add(String.format(format, "Customer", "Country", "Amount", "Tax", "Shipping"));
        lines.add("----------------------------------------------------------------------");

        for (Sale sale : salesList) {
            String name = sale.getName();
            String country = sale.getCountry();
            double amount = sale.getAmount();
            double tax = sale.getTax();
            double shipping = sale.getShipping();

            lines.add(String.format(format, name, country, amount, tax, shipping));
        }

        return String.join("\n", lines);
    }
}
