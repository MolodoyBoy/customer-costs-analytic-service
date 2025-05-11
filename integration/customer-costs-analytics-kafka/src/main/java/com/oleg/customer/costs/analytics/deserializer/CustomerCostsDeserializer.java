package com.oleg.customer.costs.analytics.deserializer;

import com.oleg.customer.costs.analytics.customer_costs.entity.CustomerCosts;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class CustomerCostsDeserializer extends JsonDeserializer<CustomerCosts> {

    public CustomerCostsDeserializer() {
        super(CustomerCosts.class);
        this.addTrustedPackages("com.oleg.customer.costs.analytics.customer_costs.entity");
    }
}