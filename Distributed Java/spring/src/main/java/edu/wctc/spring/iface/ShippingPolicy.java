package edu.wctc.spring.iface;

import edu.wctc.spring.Sale;

public interface ShippingPolicy {
    void applyShipping(Sale sale);
}
