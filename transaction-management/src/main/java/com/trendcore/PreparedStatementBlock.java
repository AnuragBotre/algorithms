package com.trendcore;

import com.trendcore.sql.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class PreparedStatementBlock {


    public static void insert(Connection connection, String sql, Table table) {

        insert(connection,sql,table, (preparedStatement, s) -> {
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                //TODO Exception handling
            }
        });
    }

    public static void insert(Connection connection, String sql, Table table, BiConsumer<PreparedStatement,String> function) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            table.getColumns().forEach(column -> {
                System.out.println(column.getType());
            });

            function.accept(preparedStatement,sql);

        } catch (SQLException e) {
            //e.printStackTrace();
            //TODO need to do exception handling
        }
    }


}
