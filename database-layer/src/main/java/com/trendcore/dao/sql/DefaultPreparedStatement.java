package com.trendcore.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Anurag on 12/25/2016.
 */
public class DefaultPreparedStatement implements CommandPreparedStatement {

    @Override
    public PreparedStatement getPreparedStatement(Connection connection, String updateQuery) throws SQLException {
        return connection.prepareStatement(updateQuery.toString());
    }

    @Override
    public void executeQuery(PreparedStatement ps) throws SQLException {
        ps.executeUpdate();
    }
}
