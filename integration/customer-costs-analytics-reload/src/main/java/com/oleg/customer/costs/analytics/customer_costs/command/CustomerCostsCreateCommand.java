package com.oleg.customer.costs.analytics.customer_costs.command;

import com.oleg.customer.costs.analytics.customer_costs.entity.CustomerCosts;

public record CustomerCostsCreateCommand(CustomerCosts customerCosts, int categorizedCostsId) {}