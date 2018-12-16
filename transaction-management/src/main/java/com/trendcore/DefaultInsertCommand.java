package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultInsertCommand {

    private Connection connection;

    public DefaultInsertCommand(Connection connection) {
        this.connection = connection;
    }

    public void insert(Table table) {

        //Need to analyze table for primary key generation

        List<Column<?>> columns = table.getColumns();

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

    public <T> void insert(TableDescriptor tableDescriptor, Row<T> row) {
        Set<Column<?>> columns = tableDescriptor.getColumns();

        //language=MYSQL-SQL
        String insert = "INSERT INTO " + tableDescriptor.getTablename() + " ";
        String columnsString = "";
        String values = "";

        Iterator<Column<?>> iterator = columns.iterator();
        while (iterator.hasNext()) {
            Column<?> column = iterator.next();
            if (iterator.hasNext()) {
                columnsString = columnsString + column.name() + ",";
                values = values + "?" + ",";
            } else {
                columnsString = columnsString + column.name();
                values = values + "?";
            }
        }

        insert = insert + "( " + columnsString + " ) VALUES (" + values + ")";

        PreparedStatementBlock.insert(connection,insert,tableDescriptor,row);
    }
}
