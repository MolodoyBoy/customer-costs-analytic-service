package com.oleg.customer.costs.analytics.cost_category;

import com.oleg.customer.costs.analytics.common.value_object.CostCategory;
import com.oleg.customer.costs.analytics.cost_category.source.AdminCustomerCostSource;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.oleg.fund.customer.costs.analytics.tables.CustomerCostsCategory.CUSTOMER_COSTS_CATEGORY;

@Repository
public class DbCostCategorySource implements AdminCustomerCostSource {

    private final DSLContext dslContext;

    public DbCostCategorySource(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public int insert(Collection<CostCategory> costCategories) {
        var records = costCategories.stream()
            .map(costCategory -> {
                Record rc = dslContext.newRecord(CUSTOMER_COSTS_CATEGORY);
                rc.set(CUSTOMER_COSTS_CATEGORY.ID, costCategory.id());
                rc.set(CUSTOMER_COSTS_CATEGORY.DESCRIPTION, costCategory.description());

                return rc;
            })
            .toList();

        return dslContext.insertInto(CUSTOMER_COSTS_CATEGORY)
            .set(records)
            .onConflict(CUSTOMER_COSTS_CATEGORY.ID)
            .doUpdate()
            .setAllToExcluded()
            .execute();
    }
}