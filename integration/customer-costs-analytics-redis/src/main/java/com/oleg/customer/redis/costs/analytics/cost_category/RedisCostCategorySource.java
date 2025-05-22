package com.oleg.customer.redis.costs.analytics.cost_category;

import com.oleg.customer.costs.analytics.common.value_object.CostCategory;
import com.oleg.customer.costs.analytics.cost_category.source.AdminCustomerCostSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class RedisCostCategorySource implements AdminCustomerCostSource {

    private static final String COST_CATEGORY_ID = "cost:category#";

    private final RedisTemplate<String, CostCategory> redisTemplate;

    @Autowired
    public RedisCostCategorySource(RedisTemplate<String, CostCategory> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public int insert(Collection<CostCategory> costCategories) {
//        redisTemplate.opsForHash().put(COST_CATEGORY_ID + id, field, value);

        return 0;
    }
}
