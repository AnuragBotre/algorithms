package com.trendcore.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public interface Table {

    String getTableName();

    default void insert(Connection connection) {
        approach1();
    }

    Object[] getRow();

    default void approach1() {


    }

    default List<Column> getColumns() {
        return null;
    }
}
