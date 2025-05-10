package com.oleg.customer.costs.analytics.customer_costs;

import com.oleg.customer.costs.analytics.common.value_object.Paginator;
import com.oleg.customer.costs.analytics.customer_costs.mapper.CustomerCostsByCategoryMapper;
import com.oleg.customer.costs.analytics.customer_costs.entity.CustomerCosts;
import com.oleg.customer.costs.analytics.customer_costs.mapper.CustomerCostsRecordMapper;
import com.oleg.customer.costs.analytics.customer_costs.query.CustomerCostsQuery;
import com.oleg.customer.costs.analytics.customer_costs.source.AdminCustomerCostsSource;
import com.oleg.customer.costs.analytics.customer_costs.source.GetCustomerCosts;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Collection;

import static com.oleg.fund.customer.costs.analytics.tables.CustomerCosts.CUSTOMER_COSTS;
import static com.oleg.fund.customer.costs.analytics.tables.CustomerCostsByCategory.CUSTOMER_COSTS_BY_CATEGORY;

@Repository
class DbCustomerCostsSource implements GetCustomerCosts, AdminCustomerCostsSource {

    private final DSLContext dslContext;
    private final CustomerCostsRecordMapper customerCostsRecordMapper;
    private final CustomerCostsByCategoryMapper customerCostsByCategoryMapper;

    public DbCustomerCostsSource(DSLContext dslContext,
                                 CustomerCostsRecordMapper customerCostsRecordMapper,
                                 CustomerCostsByCategoryMapper customerCostsByCategoryMapper) {
        this.dslContext = dslContext;
        this.customerCostsRecordMapper = customerCostsRecordMapper;
        this.customerCostsByCategoryMapper = customerCostsByCategoryMapper;
    }

    @Override
    public int insert(Collection<CustomerCosts> customerCosts) {
        return dslContext.insertInto(CUSTOMER_COSTS)
            .set(customerCostsRecordMapper.toRecords(customerCosts))
            .onConflictDoNothing()
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
            .fetch(customerCostsRecordMapper);
    }
}