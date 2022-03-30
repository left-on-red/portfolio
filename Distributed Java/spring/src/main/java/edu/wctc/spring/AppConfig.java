package edu.wctc.spring;

import edu.wctc.spring.iface.*;
import edu.wctc.spring.impl.report.*;
import edu.wctc.spring.impl.input.*;
import edu.wctc.spring.impl.shipping.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("edu.wctc.spring")
public class AppConfig {
    // 0 = file, 1 = cli
    private int salesInputMode = 0;

    // 0 = summary, 1 = detail
    private int salesReportMode = 0;

    // 0 = free shipping, 1 = flat-rate domestic shipping
    private int shippingPolicyMode = 1;

    @Bean
    public SalesReport salesReport() {
        SalesReport[] reports = { new SalesSummaryReport(), new SalesDetailReport() };
        return reports[salesReportMode];
    }

    @Bean
    public SalesInput salesInput() {
        SalesInput[] inputs = { new FileSalesInput(), new CliSalesInput() };
        return inputs[salesInputMode];
    }

    @Bean
    public ShippingPolicy shippingPolicy() {
        ShippingPolicy[] policies = { new FreeShippingPolicy(), new FlateRateDomesticShippingPolicy() };
        return policies[shippingPolicyMode];
    }
}
