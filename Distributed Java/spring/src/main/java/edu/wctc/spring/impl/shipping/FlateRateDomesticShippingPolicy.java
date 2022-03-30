package edu.wctc.spring.impl.shipping;

import edu.wctc.spring.Sale;
import edu.wctc.spring.iface.ShippingPolicy;

public class FlateRateDomesticShippingPolicy implements ShippingPolicy {
    @Override
    public void applyShipping(Sale sale) {
        String country = sale.getCountry();

        double rate = 5.99;

        switch(country.toLowerCase()) {
            case "japan": rate = 14.50; break;
            case "scotland": rate = 7.54; break;
            case "india": rate = 11.79; break;
        }

        sale.setShipping(rate);
    }
}
