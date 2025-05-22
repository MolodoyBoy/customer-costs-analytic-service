package com.oleg.customer.costs.analytics.customer_costs;

import com.oleg.customer.costs.analytics.common.value_object.Paginator;
import com.oleg.customer.costs.analytics.customer_costs.command.CreateCustomerCostsCommand;
import com.oleg.customer.costs.analytics.customer_costs.mapper.CustomerCostsByCategoryMapper;
import com.oleg.customer.costs.analytics.customer_costs.entity.CustomerCosts;
import com.oleg.customer.costs.analytics.customer_costs.mapper.CustomerCostsByPeriodMapper;
import com.oleg.customer.costs.analytics.customer_costs.mapper.CustomerCostsQueryMapper;
import com.oleg.customer.costs.analytics.customer_costs.mapper.CustomerCostsRecordMapper;
import com.oleg.customer.costs.analytics.customer_costs.query.CustomerCostsQuery;
import com.oleg.customer.costs.analytics.customer_costs.source.AdminCustomerCostsSource;
import com.oleg.customer.costs.analytics.customer_costs.source.GetCustomerCosts;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Collection;

import static com.oleg.fund.customer.costs.analytics.tables.CustomerCosts.CUSTOMER_COSTS;
import static com.oleg.fund.customer.costs.analytics.tables.CustomerCostsByPeriod.CUSTOMER_COSTS_BY_PERIOD;
import static com.oleg.fund.customer.costs.analytics.tables.CustomerCostsByCategory.CUSTOMER_COSTS_BY_CATEGORY;

@Repository
class DbCustomerCostsSource implements GetCustomerCosts, AdminCustomerCostsSource {

    private final DSLContext dslContext;
    private final CustomerCostsQueryMapper customerCostsQueryMapper;
    private final CustomerCostsRecordMapper customerCostsRecordMapper;
    private final CustomerCostsByPeriodMapper customerCostsByPeriodMapper;
    private final CustomerCostsByCategoryMapper customerCostsByCategoryMapper;

    public DbCustomerCostsSource(DSLContext dslContext,
                                 CustomerCostsQueryMapper customerCostsQueryMapper,
                                 CustomerCostsRecordMapper customerCostsRecordMapper,
                                 CustomerCostsByPeriodMapper customerCostsByPeriodMapper,
                                 CustomerCostsByCategoryMapper customerCostsByCategoryMapper) {
        this.dslContext = dslContext;
        this.customerCostsQueryMapper = customerCostsQueryMapper;
        this.customerCostsRecordMapper = customerCostsRecordMapper;
        this.customerCostsByPeriodMapper = customerCostsByPeriodMapper;
        this.customerCostsByCategoryMapper = customerCostsByCategoryMapper;
    }

    @Override
    public void bindCustomerCostsByPeriod(List<CustomerCosts> customerCosts) {
        if (customerCosts.isEmpty()) return;
        dslContext.insertInto(CUSTOMER_COSTS_BY_PERIOD)
            .set(customerCostsByPeriodMapper.toRecords(customerCosts))
            .execute();
    }

    @Override
    public void bindCustomerCostsByCategory(List<CustomerCosts> customerCosts) {
        if (customerCosts.isEmpty()) return;

        dslContext.insertInto(CUSTOMER_COSTS_BY_CATEGORY)
            .set(customerCostsByCategoryMapper.toRecords(customerCosts))
            .execute();
    }

    @Override
    public List<CustomerCosts> insert(Collection<CreateCustomerCostsCommand> commands) {
        return dslContext.insertInto(CUSTOMER_COSTS)
            .set(customerCostsRecordMapper.toRecords(commands))
            .returning()
            .fetch(customerCostsRecordMapper);
    }

    @Override
    public List<CustomerCostsQuery> getForPeriod(int periodCostsAnalyticsId) {
        return dslContext.select(
                CUSTOMER_COSTS.AMOUNT,
                CUSTOMER_COSTS.CREATED_AT,
                CUSTOMER_COSTS.DESCRIPTION
            )
            .from(CUSTOMER_COSTS)
            .innerJoin(CUSTOMER_COSTS_BY_PERIOD).on(CUSTOMER_COSTS_BY_PERIOD.CUSTOMER_COSTS_ID.eq(CUSTOMER_COSTS.ID))
            .where(CUSTOMER_COSTS_BY_PERIOD.PERIOD_COSTS_ANALYTICS_ID.eq(periodCostsAnalyticsId))
            .orderBy(CUSTOMER_COSTS.CREATED_AT.asc())
            .fetch(customerCostsQueryMapper);
    }

    @Override
    public List<CustomerCostsQuery> getForCategory(Paginator paginator, int categoryCostsAnalyticsId) {
        return dslContext.select(
                CUSTOMER_COSTS.AMOUNT,
                CUSTOMER_COSTS.CREATED_AT,
                CUSTOMER_COSTS.DESCRIPTION
            )
            .from(CUSTOMER_COSTS)
            .innerJoin(CUSTOMER_COSTS_BY_CATEGORY).on(CUSTOMER_COSTS_BY_CATEGORY.CUSTOMER_COSTS_ID.eq(CUSTOMER_COSTS.ID))
            .where(CUSTOMER_COSTS_BY_CATEGORY.CATEGORIZED_COSTS_ANALYTICS_ID.eq(categoryCostsAnalyticsId))
            .orderBy(CUSTOMER_COSTS.CREATED_AT.desc())
            .offset(paginator.skip())
            .limit(paginator.limit())
            .fetch(customerCostsQueryMapper);
    }
}