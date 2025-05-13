package com.oleg.customer.costs.analytics.deserializer;

import com.oleg.customer.costs.analytics.customer_costs.command.CreateCustomerCostsCommand;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerCostsCommandDeserializer extends JsonDeserializer<CreateCustomerCostsCommand> {

    public CreateCustomerCostsCommandDeserializer() {
        super(CreateCustomerCostsCommand.class);
    }
}