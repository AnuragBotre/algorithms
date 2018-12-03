package com.trendcore;

import com.trendcore.sql.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class DefaultInsertCommand {

    public static void insert(Connection connection, String sql, Table table) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            table.getColumns().forEach(column -> {

            });
        } catch (SQLException e) {
            //e.printStackTrace();
            //TODO need to do exception handling
        }
    }

}
