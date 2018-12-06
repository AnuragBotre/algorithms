package com.trendcore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ABC {

    private Connection connection;

    private Operation operation;

    public static class Operation {
        private PreparedStatement preparedStatement;

        private Operation1 operation1;

        public Operation(String sql) {

        }

        public Operation1 params(Object... params) {
            int cnt = 1;
            try {
                for (Object param : params) {
                    preparedStatement.setObject(cnt, param);
                    cnt++;
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            operation1 = new Operation1(preparedStatement);
            return operation1;
        }

        public void close() {
            operation1.close();
            closePreparedStatement();
        }

        private void closePreparedStatement() {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static class Operation1 {

            private PreparedStatement preparedStatement;

            private ResultSet resultSet;

            public Operation1(PreparedStatement preparedStatement) {
                this.preparedStatement = preparedStatement;
            }

            public ResultSet execute() {
                try {
                    resultSet = preparedStatement.executeQuery();
                    return resultSet;
                } catch (SQLException e) {
                    throw new RuntimeException();
                }

            }

            public void close() {
                if(resultSet != null){
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }




    public ABC connection(Connection connection) {
        return null;
    }

    public Operation query(String sql) {
        operation = new Operation(sql);
        return operation;
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = HikariDataSource.get().getDataSource().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from  actor a where actor_id = ?");

        preparedStatement.setObject(1, 1);

        ResultSet resultSet = preparedStatement.executeQuery();

        ABC abc = new ABC();

        ResultSet execute = abc.connection(connection)
                .query("select * from  actor a where actor_id = ?")
                .params(1).execute();

        abc.close();

    }

    private void close() {
        operation.close();
        closeConnection();
    }

    private void closeConnection() {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
    }

}
