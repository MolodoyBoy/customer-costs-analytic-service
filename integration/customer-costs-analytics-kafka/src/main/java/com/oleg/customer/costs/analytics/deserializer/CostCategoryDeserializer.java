package com.oleg.customer.costs.analytics.deserializer;

import com.oleg.customer.costs.data.CostCategoryData;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class CostCategoryDeserializer extends JsonDeserializer<CostCategoryData> {

    public CostCategoryDeserializer() {
        super(CostCategoryData.class);
    }
}
