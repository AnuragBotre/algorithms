package com.trendcore.dao.sql;

import java.sql.*;

/**
 * Created by Anurag on 12/25/2016.
 */
public class AutoIncrementCommandPreparedStatement implements  CommandPreparedStatement{


    @Override
    public java.sql.PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException {
        return connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    public void executeQuery(java.sql.PreparedStatement ps) throws SQLException {
        ps.executeUpdate();
        try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                handleAutoIncrementKey(generatedKeys);
            }
        }
    }

    public void handleAutoIncrementKey(ResultSet generatedKeys) throws SQLException {
    }
}
