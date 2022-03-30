package edu.wctc.spring.impl.report;

import edu.wctc.spring.Sale;
import edu.wctc.spring.iface.SalesReport;

import java.util.*;

public class SalesSummaryReport implements SalesReport {
    @Override
    public String generateReport(List<Sale> salesList) {
        List<String> lines = new ArrayList<>();

        HashMap<String, Double> amounts = new HashMap<>();
        HashMap<String, Double> taxes = new HashMap<>();
        HashMap<String, Double> shippings = new HashMap<>();

        lines.add("\nSALES SUMMARY REPORT");
        lines.add("----------------------------------------------");
        lines.add(String.format("%16s%10s%10s%10s", "Country", "Amount", "Tax", "Shipping"));
        lines.add("----------------------------------------------");

        for (Sale sale : salesList) {
            String country = sale.getCountry();
            double amount = sale.getAmount();
            double tax = sale.getTax();
            double shipping = sale.getShipping();

            if (amounts.containsKey(country)) { amount = amount + amounts.get(country); }
            if (taxes.containsKey(country)) { tax = tax + taxes.get(country); }
            if (shippings.containsKey(country)) { shipping = shipping + shippings.get(country); }

            amounts.put(country, amount);
            taxes.put(country, tax);
            shippings.put(country, shipping);
        }

        amounts.forEach((key, value) -> {
            double amount = value;
            double tax = taxes.get(key);
            double shipping = shippings.get(key);

            // rounds shipping to nearest 2 decimals to avoid inherit double imprecision
            String line = String.format("%16s%10s%10s%10s", key, amount, tax, Math.round(shipping * 100.0) / 100.0);

            lines.add(line);
        });

        return String.join("\n", lines);
    }
}
