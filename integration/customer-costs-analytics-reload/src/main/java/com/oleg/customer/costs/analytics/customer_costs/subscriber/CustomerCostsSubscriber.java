package com.oleg.customer.costs.analytics.customer_costs.subscriber;

import com.oleg.customer.costs.analytics.core.Message;
import com.oleg.customer.costs.analytics.core.Publisher;
import com.oleg.customer.costs.analytics.core.Subscriber;
import com.oleg.customer.costs.analytics.customer_costs.command.CreateCustomerCostsCommand;
import com.oleg.customer.costs.analytics.customer_costs.entity.CustomerCosts;
import com.oleg.customer.costs.analytics.customer_costs.message.CustomerCostsMessage;
import com.oleg.customer.costs.analytics.customer_costs.source.AdminCustomerCostsSource;
import com.oleg.customer.costs.analytics.period_costs.message.PeriodCostsAnalyticsMessage;
import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Component
class CustomerCostsSubscriber implements Subscriber {

    private static final Logger logger = getLogger(CustomerCostsSubscriber.class);

    private final Publisher publisher;
    private final AdminCustomerCostsSource adminCustomerCostsSource;

    CustomerCostsSubscriber(@Lazy Publisher publisher,
                            AdminCustomerCostsSource adminCustomerCostsSource) {
        this.publisher = publisher;
        this.adminCustomerCostsSource = adminCustomerCostsSource;
    }

    @Override
    @Transactional
    public void onMessage(Message m) {
        if (m instanceof CustomerCostsMessage message) {
            List<CreateCustomerCostsCommand> commands = message.commands();

            List<CustomerCosts> customerCosts = adminCustomerCostsSource.insert(commands);
            logger.info(
                "Customer costs was inserted {}.",
                customerCosts.size()
            );

            publisher.publishMessage(new PeriodCostsAnalyticsMessage(customerCosts));
        }
    }

    @Override
    public Class<? extends Message> getSupportedMessage() {
        return CustomerCostsMessage.class;
    }
}