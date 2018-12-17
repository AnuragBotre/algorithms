package com.trendcore;

import com.trendcore.sql.Row;

import java.util.stream.Collectors;

public class UpdateCommand {

    public UpdateCommand() {
    }

    public void buildQuery(TableDescriptor tableDescriptor, Row row){
        //language=MYSQL-SQL
        String query = "UPDATE " + tableDescriptor.getTablename();
        String collect = tableDescriptor.getColumns().stream()
                .filter(column -> !column.isPrimaryKey())
                .map(column -> column.name() + "=?").collect(Collectors.joining(","));

        String where = tableDescriptor.getPrimaryKeys().stream().map(column -> column.name() + " = ? ").collect(Collectors.joining(" AND "));

        query = query + collect + " WHERE " + where;
    }
}
