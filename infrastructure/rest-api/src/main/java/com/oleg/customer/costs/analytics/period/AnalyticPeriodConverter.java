package com.oleg.customer.costs.analytics.period;

import com.oleg.customer.costs.analytics.model.AnalyticPeriodDto;
import com.oleg.customer.costs.analytics.model.AnalyticPeriodsDto;
import com.oleg.customer.costs.analytics.period_costs.query.AnalyticPeriodQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnalyticPeriodConverter {

    public AnalyticPeriodsDto convert(List<AnalyticPeriodQuery> periods) {
        List<AnalyticPeriodDto> periodDtos = periods.stream()
            .map(this::convert)
            .toList();

        return new AnalyticPeriodsDto()
            .values(periodDtos);
    }

    private AnalyticPeriodDto convert(AnalyticPeriodQuery period) {
        return new AnalyticPeriodDto()
            .period(period.period().toLocalDate())
            .periodCostsAnalyticId(period.periodCostsAnalyticId());
    }
}
