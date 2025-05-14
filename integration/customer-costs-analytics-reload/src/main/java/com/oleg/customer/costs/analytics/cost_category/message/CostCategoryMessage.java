package com.oleg.customer.costs.analytics.cost_category.message;

import com.oleg.customer.costs.analytics.common.value_object.CostCategory;
import com.oleg.customer.costs.analytics.core.Message;

import java.util.List;

public record CostCategoryMessage(List<CostCategory> costCategories) implements Message {}