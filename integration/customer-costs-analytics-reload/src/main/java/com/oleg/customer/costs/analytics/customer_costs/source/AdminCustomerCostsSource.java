package com.oleg.customer.costs.analytics.customer_costs.source;

import com.oleg.customer.costs.analytics.customer_costs.command.CreateCustomerCostsCommand;
import com.oleg.customer.costs.analytics.customer_costs.entity.CustomerCosts;

import java.util.Collection;
import java.util.List;

public interface AdminCustomerCostsSource {

    void bindCustomerCostsByCategory(List<CustomerCosts> customerCosts);

    List<CustomerCosts> insert(Collection<CreateCustomerCostsCommand> commands);
}