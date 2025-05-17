package com.oleg.customer.costs.analytics.customer_costs.mapper;

import com.oleg.customer.costs.analytics.common.mapper.ToRecordMapper;
import com.oleg.customer.costs.analytics.customer_costs.command.CreateCustomerCostsCommand;
import com.oleg.customer.costs.analytics.customer_costs.entity.CustomerCosts;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import static com.oleg.fund.customer.costs.analytics.tables.CustomerCosts.CUSTOMER_COSTS;

@Component
public class CustomerCostsRecordMapper implements ToRecordMapper<CreateCustomerCostsCommand>, RecordMapper<Record, CustomerCosts> {

    private final DSLContext dslContext;

    public CustomerCostsRecordMapper(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public CustomerCosts map(Record rc) {
        return new CustomerCosts(
            rc.get(CUSTOMER_COSTS.ID),
            rc.get(CUSTOMER_COSTS.USER_ID),
            rc.get(CUSTOMER_COSTS.CATEGORY_ID),
            rc.get(CUSTOMER_COSTS.AMOUNT),
            rc.get(CUSTOMER_COSTS.DESCRIPTION),
            rc.get(CUSTOMER_COSTS.CREATED_AT)
        );
    }

    @Override
    public Record toRecord(CreateCustomerCostsCommand value) {
        Record rc = dslContext.newRecord(CUSTOMER_COSTS);

        if (value.id() != -1) {
            rc.set(CUSTOMER_COSTS.ID, value.id());
        }
        rc.set(CUSTOMER_COSTS.AMOUNT, value.amount());
        rc.set(CUSTOMER_COSTS.USER_ID, value.userId());
        rc.set(CUSTOMER_COSTS.CREATED_AT, value.createdAt());
        rc.set(CUSTOMER_COSTS.CATEGORY_ID, value.categoryId());
        rc.set(CUSTOMER_COSTS.DESCRIPTION, value.description());

        return rc;
    }
}