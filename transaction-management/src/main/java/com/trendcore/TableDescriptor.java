package com.trendcore;

import com.trendcore.sql.Column;

import java.util.HashSet;
import java.util.Set;

public class TableDescriptor {

    private Set<Column<?>> list = new HashSet<>();

    private Set<Column<?>> primaryKeys = new HashSet<>(2);

    private String tablename = "";

    public void add(Column<?> c){
        list.add(c);
    }

    public Set<Column<?>> getColumns() {
        return list;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public void setPrimaryKey(Column<Integer> id) {
        primaryKeys.add(id);
        id.setPrimaryKey(true);
    }

    public Set<Column<?>> getPrimaryKeys() {
        return primaryKeys;
    }
}
