package com.oleg.customer.costs.analytics.config;

import com.oleg.customer.costs.analytics.customer_costs.command.CreateCustomerCostsCommand;
import com.oleg.customer.costs.analytics.deserializer.CreateCustomerCostsCommandDeserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

import static org.springframework.kafka.listener.ContainerProperties.AckMode.BATCH;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaListenerErrorHandler kafkaListenerErrorHandler() {
        final Logger logger = LoggerFactory.getLogger(KafkaListenerErrorHandler.class);
        return (message, exception) -> {
            logger.error("Error occurred during processing message: {}", message, exception);
            return "Error occurred: " + exception.getMessage();
        };
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, CreateCustomerCostsCommand> customerCostsListenerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties(null);
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, CreateCustomerCostsCommandDeserializer.class);
        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.oleg.customer.costs.analytics.customer_costs.command");

        ConcurrentKafkaListenerContainerFactory<Integer, CreateCustomerCostsCommand> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setBatchListener(true);
        factory.getContainerProperties().setAckMode(BATCH);
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties));
        factory.getContainerProperties().setListenerTaskExecutor(asyncTaskExecutor());

        return factory;
    }

    private AsyncTaskExecutor asyncTaskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(1);
        executor.setVirtualThreads(true);

        return executor;
    }
}