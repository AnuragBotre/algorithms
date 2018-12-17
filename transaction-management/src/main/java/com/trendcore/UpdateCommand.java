package com.trendcore;

import com.trendcore.sql.Row;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateCommand {

    private Connection connection;

    public UpdateCommand(Connection connection) {
        this.connection = connection;
    }

    public void execute(TableDescriptor tableDescriptor, List<Row> rows) {
        //language=MYSQL-SQL
        String query = "UPDATE " + tableDescriptor.getTablename() + " SET ";
        String valuesToBeUpdated = tableDescriptor.getColumns().stream()
                .filter(column -> !column.isPrimaryKey())
                .map(column -> column.name() + "=?").collect(Collectors.joining(","));

        String primaryKeys = tableDescriptor.getPrimaryKeys().stream().map(column -> column.name() + " = ? ").collect(Collectors.joining(" AND "));

        query = query + valuesToBeUpdated + " WHERE " + primaryKeys;

        PreparedStatementBlock.update(connection,query,tableDescriptor,rows);


    }
}
