package com.oleg.customer.costs.analytics.categorized_costs.source;

import com.oleg.customer.costs.analytics.categorized_costs.entity.CategorizedCostsAnalytics;
import com.oleg.customer.costs.analytics.categorized_costs.command.CategorizedCostAnalyticsCreateCommand;

import java.util.Map;
import java.util.Collection;

public interface AdminCategorizedCostsAnalyticsSource {

    int update(Collection<CategorizedCostsAnalytics> categorizedCostsAnalytics);

    Map<CategorizedCostAnalyticsCreateCommand, CategorizedCostsAnalytics> get(Collection<CategorizedCostAnalyticsCreateCommand> keys);

    Map<CategorizedCostAnalyticsCreateCommand, CategorizedCostsAnalytics> create(Collection<CategorizedCostAnalyticsCreateCommand> keys);
}