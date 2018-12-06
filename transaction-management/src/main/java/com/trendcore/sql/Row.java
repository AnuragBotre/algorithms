package com.trendcore.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public interface Row<T> {

    <R> void set(Column<R> column,R r);

    <R> R get(Column<R> id);

    void populate(ResultSet resultSet);
}
