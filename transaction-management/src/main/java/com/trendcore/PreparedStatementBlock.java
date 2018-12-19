package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Seq;
import com.trendcore.sql.Table;

import java.sql.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PreparedStatementBlock {


    //TODO - remove me
    public static void insert(Connection connection, String sql, Table table) {

        insert(connection, sql, table, (preparedStatement, s) -> {
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                //TODO Exception handling
            }
        });
    }

    //TODO - remove me
    public static void insert(Connection connection, String sql, Table table, BiConsumer<PreparedStatement, String> function) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            table.getColumns().forEach(column -> {
                System.out.println(column.getType());
            });

            function.accept(preparedStatement, sql);

        } catch (SQLException e) {
            //e.printStackTrace();
            //TODO need to do exception handling
        }
    }

    public static void insert(Connection connection, String sql, TableDescriptor tableDescriptor, Row row, BiConsumer<PreparedStatement, String> function) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            Seq seq = new Seq(1);
            tableDescriptor.getColumns().forEach(column -> bindPreparedStatement(preparedStatement, column, row, seq.next()));

            function.accept(preparedStatement, sql);

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO need to do exception handling
        }
    }

    private static void bindPreparedStatement(PreparedStatement preparedStatement, Column column, Row row, Integer index) {
        try {


            Object o = row.get(column);

            if (column.getType().isAssignableFrom(Integer.class)) {
                preparedStatement.setInt(index, (Integer) o);
            } else if (column.getType().isAssignableFrom(String.class)) {
                preparedStatement.setString(index, (String) o);
            } else if (column.getType().isAssignableFrom(Long.class)) {
                preparedStatement.setLong(index, (Long) o);
            } else if (column.getType().isAssignableFrom(Date.class)) {
                preparedStatement.setDate(index, (Date) o);
            } else if (column.getType().isAssignableFrom(Double.class)) {
                preparedStatement.setDouble(index, (Double) o);
            } else if (column.getType().isAssignableFrom(Timestamp.class)) {
                preparedStatement.setTimestamp(index, (Timestamp) o);
            } else if (column.getType().isAssignableFrom(Clob.class)) {
                //TODO Need to handle clob
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            //TODO need to do exception handling
        }

    }


    public static <T> void insert(Connection connection, String sql, TableDescriptor tableDescriptor, Row<T> row) {
        insert(connection, sql, tableDescriptor, row, (preparedStatement, s) -> {
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Exception handling
            }
        });
    }

    public static void update(Connection connection, String query, TableDescriptor tableDescriptor, List<Row> rows, Predicate<Column<?>> columnPredicate, Function<Column<?>, String> whereClause) {
        update(connection,query,tableDescriptor,rows,columnPredicate,whereClause, (preparedStatement, s) -> {
            try {
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                //TODO Exception handling
                e.printStackTrace();
            }
        });
    }

    private static void update(Connection connection, String sql, TableDescriptor tableDescriptor, List<Row> rows, Predicate<Column<?>> columnPredicate, Function<Column<?>, String> whereClause, BiConsumer<PreparedStatement, String> biConsumer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (Row row : rows) {
                Seq seq = new Seq(1);
                tableDescriptor.getColumns().stream().filter(columnPredicate).forEach(column -> bindPreparedStatement(preparedStatement, column, row, seq.next()));

                tableDescriptor.getPrimaryKeys().stream().forEach(column -> bindPreparedStatement(preparedStatement, column, row, seq.next()));
                preparedStatement.addBatch();
            }
            biConsumer.accept(preparedStatement, sql);

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO need to do exception handling
        }
    }
}
