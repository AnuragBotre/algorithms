package com.trendcore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SingleResultSetWrapper {

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public SingleResultSetWrapper(ResultSet resultSet, PreparedStatement preparedStatement) {
        this.resultSet = resultSet;
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void close() {
        Closeable.close(resultSet);
        resultSet = null;
        Closeable.close(preparedStatement);
        preparedStatement = null;
    }
}
