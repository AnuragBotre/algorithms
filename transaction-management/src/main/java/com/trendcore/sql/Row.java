package com.trendcore.sql;

public interface Row<T> {

    <R> void set(Column<R> column,R r);

    <R> R get(Column<R> id);
}
