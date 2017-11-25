package com.trendcore.dao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anurag on 12/17/2016.
 */
public class DatabaseRow {

    private Map<Column<?>, Object> columns = new LinkedHashMap<>();

    private String tablename;

    public DatabaseRow(String tablename) {
        this.tablename = tablename;
    }

    public void putColumn(Column<?> type) {
        columns.put(type, null);
    }

    public <T> void putColumn(Column<T> type, T instance) {
        if (type == null)
            throw new NullPointerException("Type is null");

        // Achieving runtime type safety with a dynamic cast
        // Same trick can be found in the following implementations
        // checkedSet, checkedList, checkedMap, and so forth.
        columns.put(type, instance.getClass().cast(instance));

    }

    public <T> T getColumn(Column<T> type) {

        return type.cast(columns.get(type));
    }

    public Map<Column<?>, Object> getColumns() {
        return columns;
    }

    public String getTablename() {
        return tablename;
    }
}
