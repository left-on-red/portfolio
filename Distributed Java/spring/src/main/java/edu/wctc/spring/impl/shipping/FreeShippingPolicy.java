package edu.wctc.spring.impl.shipping;

import edu.wctc.spring.Sale;
import edu.wctc.spring.iface.ShippingPolicy;

public class FreeShippingPolicy implements ShippingPolicy {
    @Override
    public void applyShipping(Sale sale) {
        sale.setShipping(0.0);
    }
}
