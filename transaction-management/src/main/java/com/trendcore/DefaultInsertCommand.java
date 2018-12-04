package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DefaultInsertCommand {

    private Connection connection;

    public DefaultInsertCommand(Connection connection) {
        this.connection = connection;
    }

    public void insert(Table table) {

        //Need to analyze table for primary key generation

        List<Column> columns = table.getColumns();

        //language=MYSQL-SQL
        String insert = "INSERT INTO " + table.getTableName() + " ";
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

        PreparedStatementBlock.insert(connection,insert,table);


    }

}
