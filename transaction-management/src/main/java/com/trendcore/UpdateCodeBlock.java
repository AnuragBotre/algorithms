package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UpdateCodeBlock {

    public static class Actor {
        public static Column<Integer> actor_id;
        public static Column<String> first_name;
        public static Column<String> last_name;
        public static Column<Timestamp> last_update;
    }

    public static void main(String[] args) throws SQLException {

        DataSource test = HikariDataSource.get("test").getDataSource();

        String sql = "update actor set first_name = ?, last_name = ? where actor_id = ?";

        createPrepareStatement(test, sql);

        QueryExecutor queryExecutor = new QueryExecutor(test.getConnection());

        Row<Actor> row = new Tuple<>(5);

        Actor actor = new Actor();
        Table.init(Actor.class);
        row.set(actor.first_name, "Anurag");
        row.set(actor.last_name, "B");
        row.set(actor.last_update, new Timestamp(System.currentTimeMillis()));

        queryExecutor.execute("update actor set first_name = ?, last_name = ? where actor_id = ?", () -> actor.first_name.val(row), () -> actor.last_name.val(row), () -> actor.actor_id.val(row));


        List rows = new ArrayList();
        rows.add(row);

        queryExecutor.execute("update actor set first_name = ?, last_name = ? where actor_id = ?", rows.stream(), actor.first_name, actor.last_name, actor.actor_id);
    }


    @FunctionalInterface
    public interface PreparedStatementSetter<T> {
        void set(PreparedStatement preparedStatement, Integer i, T t) throws SQLException;
    }


    public static class QueryExecutor {

        private Connection connection;

        private Map<Class, PreparedStatementSetter> functionMap = new HashMap(10);
        private Map<Class<String>, Integer> sqlTypes = new HashMap<Class<String>, Integer>(10);

        {
            Function<Date, java.sql.Date> dateConverter = date -> new java.sql.Date(date.getTime());

            functionMap.put(String.class, (PreparedStatementSetter<String>) PreparedStatement::setString);
            functionMap.put(Integer.class, (PreparedStatementSetter<Integer>) PreparedStatement::setInt);
            functionMap.put(Long.class, (PreparedStatementSetter<Long>) PreparedStatement::setLong);

            PreparedStatementSetter<Date> datePreparedStatementSetter = (preparedStatement, i, date) -> preparedStatement.setDate(i, dateConverter.apply(date));
            functionMap.put(Date.class, datePreparedStatementSetter);

            functionMap.put(Timestamp.class, (PreparedStatementSetter<Timestamp>) PreparedStatement::setTimestamp);
            functionMap.put(Double.class, (PreparedStatementSetter<Double>) PreparedStatement::setDouble);
            functionMap.put(Float.class, (PreparedStatementSetter<Float>) PreparedStatement::setFloat);

            sqlTypes.put(String.class,Types.VARCHAR);
        }


        public QueryExecutor(Connection connection) {
            this.connection = connection;
        }

        public void execute(String query, Supplier... suppliers) {

        }

        public void execute(String s, Stream<Row> rows, Column... values) {


            PreparedStatement ps = null;

            rows.forEach(row -> {
                try {
                    for (int i = 0, cnt = 1; i < values.length; i++, cnt++) {
                        Object o = values[i].val(row);

                        if (o != null) {

                            functionMap.get(o.getClass()).set(ps, cnt, o);
                        } else {
                            //set null in prepared statement
                            ps.setNull(cnt, sqlTypes.get(values[i].getType()));
                        }

                        //ps.setInt(cnt,o);
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    private static void createPrepareStatement(DataSource test, String sql) {
        try {
            PreparedStatement preparedStatement = test.getConnection().prepareStatement(sql);

            preparedStatement.clearParameters();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
