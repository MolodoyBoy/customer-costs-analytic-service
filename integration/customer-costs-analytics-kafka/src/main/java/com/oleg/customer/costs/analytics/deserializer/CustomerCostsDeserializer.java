package com.oleg.customer.costs.analytics.deserializer;

import com.oleg.customer.costs.data.CustomerCostsData;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class CustomerCostsDeserializer extends JsonDeserializer<CustomerCostsData> {

    public CustomerCostsDeserializer() {
        super(CustomerCostsData.class);
    }
}