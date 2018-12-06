package com.trendcore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLOperationUsingFunctionWay {



    public static void main(String[] args) throws SQLException {


        Connection connection = HikariDataSource.get().getDataSource().getConnection();

        /*PreparedStatement preparedStatement = connection.prepareStatement("select * from  actor a where actor_id = ?");
        preparedStatement.setObject(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();*/

        SQLOperationUsingFunctionWay sqlOperationUsingFunctionWay = new SQLOperationUsingFunctionWay();
        ResultSet resultSet1 = sqlOperationUsingFunctionWay.with(connection).query("select * from  actor a where actor_id = ?",1).execute();
        sqlOperationUsingFunctionWay.close();

        Step1 s = new Step1(connection);
        Step2 step2 = s.getPreparedStatement(connection, "select * from  actor a where actor_id = ?", 1);
        ResultSet resultSet2 = step2.getResultSet();

        s.close();
    }

    private void close() {

    }

    private Query query;
    private Query with(Connection connection) {
        query = new Query(connection);
        return query;
    }

    public static class Query{
        private Connection connection;
        public Query(Connection connection) {
            this.connection = connection;
        }

        public Execute query(String sql, Object... params) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  actor a where actor_id = ?");
            int cnt = 1;
            for (Object param : params) {
                preparedStatement.setObject(cnt, param);
                cnt++;
            }
            return new Execute(preparedStatement);
        }
    }

    public static class Execute{

        private PreparedStatement preparedStatement;

        private ResultSet resultSet;

        public Execute(PreparedStatement preparedStatement) {
            this.preparedStatement = preparedStatement;
        }

        public ResultSet execute() throws SQLException {
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
    }


    public static class Step1 implements AutoCloseable {
        private Connection connection;

        List<Step2> step2List = new ArrayList();

        public Step1(Connection connection) {
            this.connection = connection;
        }

        public Step2 getPreparedStatement(Connection connection, String sql, Object... params) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  actor a where actor_id = ?");
            int cnt = 1;
            for (Object param : params) {
                preparedStatement.setObject(cnt, param);
                cnt++;
            }
            Step2 step2 = new Step2(preparedStatement);
            step2List.add(step2);
            return step2;
        }

        public void close() {
            if (step2List != null && !step2List.isEmpty()) {
                for(Step2 s : step2List){
                    s.close();
                }
            }
        }
    }

    public static class Step2 {

        private PreparedStatement preparedStatement;

        private ResultSet resultSet;

        public Step2(PreparedStatement preparedStatement) {
            this.preparedStatement = preparedStatement;
        }

        public ResultSet getResultSet() throws SQLException {
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }

        public void close() {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
