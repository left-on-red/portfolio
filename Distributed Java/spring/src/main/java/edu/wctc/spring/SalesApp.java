package edu.wctc.spring;

import edu.wctc.spring.iface.SalesInput;
import edu.wctc.spring.iface.SalesReport;
import edu.wctc.spring.iface.ShippingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesApp {
    private SalesInput salesInput;
    private SalesReport salesReport;
    private ShippingPolicy shippingPolicy;

    @Autowired
    public SalesApp(SalesInput salesInput, SalesReport salesReport, ShippingPolicy shippingPolicy) {
        this.salesInput = salesInput;
        this.salesReport = salesReport;
        this.shippingPolicy = shippingPolicy;
    }

    public void generateReport() {
        List<Sale> sales = salesInput.getSales();
        for (Sale sale : sales) { shippingPolicy.applyShipping(sale); }
        System.out.println(salesReport.generateReport(sales));
    }
}
