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
        List<Column> columns = getColumns();

        PreparedStatement preparedStatement;
        //language=MYSQL-SQL
        String insert = "INSERT INTO " + getTableName() + " ";
        String columnsString = "";
        String values = "";

        for (int i = 0; i < columns.size(); i++) {
            if (i < columns.size() - 1) {
                columnsString = columnsString + columns.get(i).name() + ",";
                values = values + "?" + ",";
            } else {
                columnsString = columnsString + columns.get(i).name();
                values = values + "?";
            }
        }

        insert = insert + "( " + columnsString + " ) VALUES (" + values + ")";
        System.out.println(insert);
    }

    default List<Column> getColumns() {
        return null;
    }
}
