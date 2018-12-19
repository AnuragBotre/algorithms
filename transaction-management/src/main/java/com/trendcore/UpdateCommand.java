package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UpdateCommand {

    private Connection connection;

    public UpdateCommand(Connection connection) {
        this.connection = connection;
    }

    public void execute(TableDescriptor tableDescriptor, List<Row> rows) {
        //language=MYSQL-SQL
        String query = "UPDATE " + tableDescriptor.getTablename() + " SET ";
        Predicate<Column<?>> columnPredicate = column -> !column.isPrimaryKey();
        String valuesToBeUpdated = tableDescriptor.getColumns().stream()
                .filter(columnPredicate)
                .map(column -> column.name() + "=?").collect(Collectors.joining(","));

        Function<Column<?>, String> whereClause = column -> column.name() + " = ? ";
        String primaryKeys = tableDescriptor.getPrimaryKeys().stream().map(whereClause).collect(Collectors.joining(" AND "));

        query = query + valuesToBeUpdated + " WHERE " + primaryKeys;

        PreparedStatementBlock.update(connection,query,tableDescriptor,rows , columnPredicate , whereClause);


    }
}
