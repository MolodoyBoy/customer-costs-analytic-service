package com.oleg.customer.costs.analytics.period_costs.usecase;

import com.oleg.customer.costs.analytics.categorized_costs.source.GetCategorizedCostsAnalyticsSource;
import com.oleg.customer.costs.analytics.common.exception.NotFoundException;
import com.oleg.customer.costs.analytics.customer_costs.source.GetCustomerCosts;
import com.oleg.customer.costs.analytics.period_costs.source.GetAnalyticPeriodSource;
import com.oleg.customer.costs.analytics.period_costs.source.GetPeriodCostsAnalyticsSource;
import com.oleg.customer.costs.analytics.period_costs.query.AnalyticPeriodQuery;
import com.oleg.customer.costs.analytics.period_costs.value_object.PeriodCostsAnalyticsWithCategories;
import com.oleg.customer.costs.analytics.user.UserContext;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.List;

import static com.oleg.customer.costs.analytics.categorized_costs.colum.CategorizedCostsAnalyticsColumn.*;

@Service
public class PeriodCostsAnalyticsUseCase {

    private static final int CATEGORIZED_COSTS_ANALYTICS_LIMIT = 5;

    private final UserContext userContext;
    private final GetCustomerCosts getCustomerCosts;
    private final GetAnalyticPeriodSource getAnalyticPeriodSource;
    private final GetPeriodCostsAnalyticsSource getPeriodCostsAnalyticsSource;
    private final GetCategorizedCostsAnalyticsSource getCategorizedCostsAnalyticsSource;

    public PeriodCostsAnalyticsUseCase(UserContext userContext,
                                       GetCustomerCosts getCustomerCosts,
                                       GetAnalyticPeriodSource getAnalyticPeriodSource,
                                       GetPeriodCostsAnalyticsSource getPeriodCostsAnalyticsSource,
                                       GetCategorizedCostsAnalyticsSource getCategorizedCostsAnalyticsSource) {
        this.userContext = userContext;
        this.getCustomerCosts = getCustomerCosts;
        this.getAnalyticPeriodSource = getAnalyticPeriodSource;
        this.getPeriodCostsAnalyticsSource = getPeriodCostsAnalyticsSource;
        this.getCategorizedCostsAnalyticsSource = getCategorizedCostsAnalyticsSource;
    }

    public List<AnalyticPeriodQuery> get() {
        int userId = userContext.id();
        List<AnalyticPeriodQuery> analyticPeriods = getAnalyticPeriodSource.getAnalyticPeriods(userId);
        if (analyticPeriods.isEmpty()) {
            throw new NotFoundException("No analytic periods found for user.");
        }

        return analyticPeriods;
    }

    public PeriodCostsAnalyticsWithCategories get(int id, Integer limit) {
        var periodCostsAnalytics = getPeriodCostsAnalyticsSource.get(id);
        if (periodCostsAnalytics == null) {
            throw new NotFoundException("No period costs analytics found for id: " + id);
        }

        if (limit == null) {
            limit = CATEGORIZED_COSTS_ANALYTICS_LIMIT;
        }

        var forPeriod = getCustomerCosts.getForPeriod(id);
        var columns = Set.of(ID, AMOUNT, PERCENT, TRANSACTIONS_COUNT, CATEGORY_DESCRIPTION);
        var categorizedCostsAnalytics = getCategorizedCostsAnalyticsSource.getForPeriod(limit, id, columns);

        return new PeriodCostsAnalyticsWithCategories(forPeriod, periodCostsAnalytics, categorizedCostsAnalytics);
    }
}