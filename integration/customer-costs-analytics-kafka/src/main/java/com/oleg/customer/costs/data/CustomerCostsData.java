package com.oleg.customer.costs.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CustomerCostsData(
    int userId,
    int categoryId,
    BigDecimal amount,
    String description,
    LocalDateTime createdAt
) {
}
