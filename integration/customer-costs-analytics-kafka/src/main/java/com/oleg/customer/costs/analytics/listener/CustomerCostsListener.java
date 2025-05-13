package com.oleg.customer.costs.analytics.listener;

import com.oleg.customer.costs.analytics.core.Publisher;
import com.oleg.customer.costs.analytics.customer_costs.command.CreateCustomerCostsCommand;
import com.oleg.customer.costs.analytics.customer_costs.message.CustomerCostsMessage;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
class CustomerCostsListener {

    private final Publisher publisher;

    CustomerCostsListener(Publisher publisher) {
        this.publisher = publisher;
    }

    @KafkaListener(
        clientIdPrefix = "${oleg.kafka.customer-costs.id}",
        groupId = "${oleg.kafka.customer-costs.consumer.group.id}",
        topics = "${oleg.kafka.customer-costs.topic}",
        errorHandler = "kafkaListenerErrorHandler",
        batch = "true",
        containerFactory = "customerCostsListenerFactory"
    )
    public void listenCustomerCosts(@Payload ConsumerRecords<Integer, CreateCustomerCostsCommand> consumerRecords) {
        List<CreateCustomerCostsCommand> customerCosts = new ArrayList<>(consumerRecords.count());

        consumerRecords.forEach(c -> customerCosts.add(c.value()));

        publisher.publishMessage(new CustomerCostsMessage(customerCosts));
    }
}