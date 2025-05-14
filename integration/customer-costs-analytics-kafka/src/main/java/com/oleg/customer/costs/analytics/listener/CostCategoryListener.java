package com.oleg.customer.costs.analytics.listener;

import com.oleg.customer.costs.analytics.common.value_object.CostCategory;
import com.oleg.customer.costs.analytics.core.Publisher;
import com.oleg.customer.costs.analytics.cost_category.message.CostCategoryMessage;
import com.oleg.customer.costs.data.CostCategoryData;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class CostCategoryListener {

    private final Publisher publisher;

    CostCategoryListener(Publisher publisher) {
        this.publisher = publisher;
    }

    @KafkaListener(
        clientIdPrefix = "${oleg.kafka.costs-category.id}",
        groupId = "${oleg.kafka.costs-category.consumer.group.id}",
        topics = "${oleg.kafka.costs-category.topic}",
        errorHandler = "kafkaListenerErrorHandler",
        batch = "true",
        containerFactory = "costCategoryListenerFactory"
    )
    public void listenCustomerCosts(@Payload ConsumerRecords<Integer, CostCategoryData> consumerRecords) {
        List<CostCategory> costCategories = new ArrayList<>(consumerRecords.count());

        consumerRecords.forEach(c -> {
            CostCategoryData data = c.value();
            costCategories.add(convert(data));
        });

        publisher.publishMessage(new CostCategoryMessage(costCategories));
    }

    private CostCategory convert(CostCategoryData data) {
        return new CostCategory(data.id(), data.description());
    }
}