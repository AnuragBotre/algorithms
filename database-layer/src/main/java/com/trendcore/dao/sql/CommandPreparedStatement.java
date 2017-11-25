package com.trendcore.dao.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Anurag on 12/25/2016.
 */
public interface CommandPreparedStatement {

    java.sql.PreparedStatement getPreparedStatement(Connection connection, String updateQuery) throws SQLException;

    void executeQuery(java.sql.PreparedStatement ps) throws SQLException;
}
