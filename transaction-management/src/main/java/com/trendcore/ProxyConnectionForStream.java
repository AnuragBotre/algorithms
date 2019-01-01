package com.trendcore;

import com.trendcore.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProxyConnectionForStream {

    private Connection connection;

    List<ResultSetHolder> resultSets;

    public static class ResultSetHolder {

        private SingleResultSetWrapper resultSetWrapper;


        public ResultSetHolder(SingleResultSetWrapper resultSetWrapper) {
            this.resultSetWrapper = resultSetWrapper;
        }

        public void close() {
            this.resultSetWrapper.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void close() {

        closePreparedStatementsOnThisConnection();
        closeConnection();
    }

    private void closePreparedStatementsOnThisConnection() {
        if (resultSets != null && !resultSets.isEmpty()) {
            Iterator<ResultSetHolder> iterator = resultSets.iterator();
            while (iterator.hasNext()) {
                ResultSetHolder resultSetHolder = iterator.next();
                resultSetHolder.close();
                iterator.remove();
            }
        }
    }

    public void closeConnection() {
        Closeable.close(connection);
        connection = null;
    }

    public SingleResultSetWrapper getResultSet(String sql, Object[] params) {
        if (resultSets == null) {
            resultSets = new ArrayList<>(2);
        }


        System.out.println("Executing Query :- " + sql);
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        setValuesOnPreparedStatement(params, preparedStatement);

        ResultSet resultSet = getResultSet(preparedStatement);
        SingleResultSetWrapper resultSetWrapper = new SingleResultSetWrapper(resultSet, preparedStatement);
        resultSets.add(new ResultSetHolder(resultSetWrapper));
        return resultSetWrapper;

    }

    public ResultSet getResultSet(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw SystemException.wrap(e, () -> DatabaseErrorCode.QUERY_EXECUTION , DatabaseErrorCode.CATEGORY);
        }
    }

    public void setValuesOnPreparedStatement(Object[] params, PreparedStatement preparedStatement) {
        try {
            int cnt = 1;
            for (Object param : params) {
                preparedStatement.setObject(cnt, param);
                cnt++;
            }
        } catch (SQLException e) {
            throw SystemException.wrap(e, () -> DatabaseErrorCode.INVALID_PARAMS_FOR_PREPARED_STATEMENT , DatabaseErrorCode.CATEGORY);
        }
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw SystemException.wrap(e, () -> DatabaseErrorCode.INVALID_QUERY , DatabaseErrorCode.CATEGORY);
        }
    }
}
