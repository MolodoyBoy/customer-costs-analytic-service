package com.oleg.customer.costs.analytics.deserializer;

import com.oleg.customer.costs.analytics.common.value_object.CostCategory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class CostCategoryDeserializer extends JsonDeserializer<CostCategory> {

    public CostCategoryDeserializer() {
        super(CostCategory.class);
    }
}
