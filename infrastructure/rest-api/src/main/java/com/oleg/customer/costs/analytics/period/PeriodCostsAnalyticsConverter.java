package com.oleg.customer.costs.analytics.period;

import com.oleg.customer.costs.analytics.categorized_costs.snapshot.CategorizedCostsAnalyticsSnapshot;
import com.oleg.customer.costs.analytics.model.CategorizedCostsAnalyticsDto;
import com.oleg.customer.costs.analytics.model.PeriodCostsAnalyticsDto;
import com.oleg.customer.costs.analytics.model.PeriodCostsAnalyticsResponseDto;
import com.oleg.customer.costs.analytics.period_costs.snapshot.PeriodCostsAnalyticsSnapshot;
import com.oleg.customer.costs.analytics.period_costs.value_object.PeriodCostsAnalyticsWithCategories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeriodCostsAnalyticsConverter {

    public PeriodCostsAnalyticsResponseDto convert(PeriodCostsAnalyticsWithCategories entity) {
        return new PeriodCostsAnalyticsResponseDto()
            .periodCostsAnalytics(convert(entity.periodCostsAnalytics()))
            .categorizedCostsAnalytics(convert(entity.categorizedCostsAnalytics()));
    }

    private PeriodCostsAnalyticsDto convert(PeriodCostsAnalyticsSnapshot entity) {
        return new PeriodCostsAnalyticsDto()
            .id(entity.id())
            .amount(entity.amount().doubleValue())
            .period(entity.period().toLocalDate())
            .differenceFromPrevious(entity.differenceFromPrevious().doubleValue());
    }

    private List<CategorizedCostsAnalyticsDto> convert(List<CategorizedCostsAnalyticsSnapshot> entities) {
        return entities.stream()
            .map(this::convert)
            .toList();
    }

    private CategorizedCostsAnalyticsDto convert(CategorizedCostsAnalyticsSnapshot entity) {
        return new CategorizedCostsAnalyticsDto()
            .id(entity.id())
            .amount(entity.amount().doubleValue())
            .percent(entity.percent().doubleValue())
            .transactionsCount(entity.transactionsCount())
            .categoryDescription(entity.categoryDescription());
    }
}
