package com.oleg.customer.costs.analytics.cost_category.subscruber;

import com.oleg.customer.costs.analytics.core.Message;
import com.oleg.customer.costs.analytics.core.Subscriber;
import com.oleg.customer.costs.analytics.cost_category.message.CostCategoryMessage;
import com.oleg.customer.costs.analytics.cost_category.source.AdminCustomerCostSource;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
class CostCategorySubscriber implements Subscriber {

    private static final Logger logger = getLogger(CostCategorySubscriber.class);

    private final AdminCustomerCostSource adminCustomerCostSource;

    public CostCategorySubscriber(AdminCustomerCostSource adminCustomerCostSource) {
        this.adminCustomerCostSource = adminCustomerCostSource;
    }

    @Override
    public void onMessage(Message m) {
        if (m instanceof CostCategoryMessage message) {
            logger.info(
                "Cost category was inserted {}.",
                adminCustomerCostSource.insert(message.costCategories())
            );
        }
    }

    @Override
    public Class<? extends Message> getSupportedMessage() {
        return CostCategoryMessage.class;
    }
}