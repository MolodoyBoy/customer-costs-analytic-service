package com.oleg.customer.costs.analytics.cost_category.source;

import com.oleg.customer.costs.analytics.common.value_object.CostCategory;

import java.util.Collection;

public interface AdminCustomerCostSource {

    int insert(Collection<CostCategory> costCategories);
}
