package com.trendcore.sql;

public class Row<T> {

    private Object arr[];
    public T t;

    public <R> R get(Column<R> col) {
        System.out.println(col.name());
        return null;
    }
}
