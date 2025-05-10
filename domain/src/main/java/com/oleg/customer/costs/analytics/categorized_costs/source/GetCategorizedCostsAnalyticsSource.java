package com.oleg.customer.costs.analytics.categorized_costs.source;

import com.oleg.customer.costs.analytics.categorized_costs.colum.CategorizedCostsAnalyticsColumn;
import com.oleg.customer.costs.analytics.categorized_costs.snapshot.CategorizedCostsAnalyticsSnapshot;

import java.util.Set;
import java.util.List;

public interface GetCategorizedCostsAnalyticsSource {

    CategorizedCostsAnalyticsSnapshot get(int id, Set<CategorizedCostsAnalyticsColumn> columns);

    List<CategorizedCostsAnalyticsSnapshot> getForPeriod(int limit, int periodCostsAnalyticsId, Set<CategorizedCostsAnalyticsColumn> columns);
}