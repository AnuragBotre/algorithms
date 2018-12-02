package com.trendcore.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Transaction {

    public void execute(Connection connection) throws SQLException;
}
