package com.oleg.customer.costs.analytics.column.context;

import com.oleg.customer.costs.analytics.common.column.Column;
import com.oleg.customer.costs.analytics.column.binding.ColumnBinding;
import org.jooq.Field;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toMap;
import static java.util.function.Function.identity;

@Component
public class ColumnContextImpl implements ColumnContext {

    private final Map<Class<?>, ColumnBinding> columnBindings;

    public ColumnContextImpl(List<ColumnBinding> columnBindings) {
        this.columnBindings = columnBindings.stream()
            .collect(toMap(ColumnBinding::entity, identity()));
    }

    @Override
    public List<Field<?>> getFields(Collection<? extends Column> columns) {
        List<Field<?>> result = new ArrayList<>();

        columns.stream()
            .map(c -> {
                ColumnBinding columnBinding = columnBindings.get(c.entity());
                if (columnBinding == null) return null;

                return columnBinding.getField(c);
            })
            .filter(Objects::nonNull)
            .forEach(result::add);

        return result;
    }
}