/*
 * This file is generated by jOOQ.
 */
package com.oleg.fund.customer.costs.analytics.tables;


import com.oleg.fund.customer.costs.analytics.Public;

import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CustomerCostsByCategory extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.customer_costs_by_category</code>
     */
    public static final CustomerCostsByCategory CUSTOMER_COSTS_BY_CATEGORY = new CustomerCostsByCategory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>public.customer_costs_by_category.customer_costs_id</code>.
     */
    public final TableField<Record, Integer> CUSTOMER_COSTS_ID = createField(DSL.name("customer_costs_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column
     * <code>public.customer_costs_by_category.categorized_costs_analytics_id</code>.
     */
    public final TableField<Record, Integer> CATEGORIZED_COSTS_ANALYTICS_ID = createField(DSL.name("categorized_costs_analytics_id"), SQLDataType.INTEGER.nullable(false), this, "");

    private CustomerCostsByCategory(Name alias, Table<Record> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private CustomerCostsByCategory(Name alias, Table<Record> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.customer_costs_by_category</code> table
     * reference
     */
    public CustomerCostsByCategory(String alias) {
        this(DSL.name(alias), CUSTOMER_COSTS_BY_CATEGORY);
    }

    /**
     * Create an aliased <code>public.customer_costs_by_category</code> table
     * reference
     */
    public CustomerCostsByCategory(Name alias) {
        this(alias, CUSTOMER_COSTS_BY_CATEGORY);
    }

    /**
     * Create a <code>public.customer_costs_by_category</code> table reference
     */
    public CustomerCostsByCategory() {
        this(DSL.name("customer_costs_by_category"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public CustomerCostsByCategory as(String alias) {
        return new CustomerCostsByCategory(DSL.name(alias), this);
    }

    @Override
    public CustomerCostsByCategory as(Name alias) {
        return new CustomerCostsByCategory(alias, this);
    }

    @Override
    public CustomerCostsByCategory as(Table<?> alias) {
        return new CustomerCostsByCategory(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CustomerCostsByCategory rename(String name) {
        return new CustomerCostsByCategory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CustomerCostsByCategory rename(Name name) {
        return new CustomerCostsByCategory(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CustomerCostsByCategory rename(Table<?> name) {
        return new CustomerCostsByCategory(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CustomerCostsByCategory where(Condition condition) {
        return new CustomerCostsByCategory(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CustomerCostsByCategory where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CustomerCostsByCategory where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CustomerCostsByCategory where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CustomerCostsByCategory where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CustomerCostsByCategory where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CustomerCostsByCategory where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CustomerCostsByCategory where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CustomerCostsByCategory whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CustomerCostsByCategory whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
