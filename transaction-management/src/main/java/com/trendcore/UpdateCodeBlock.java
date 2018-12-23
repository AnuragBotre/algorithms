package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.function.Function;
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
        row.set(actor.actor_id, 1);
        row.set(actor.first_name, "Anurag");
        row.set(actor.last_name, "B");
        row.set(actor.last_update, new Timestamp(System.currentTimeMillis()));

        //This is working
        queryExecutor.execute("update actor set first_name = ?, last_name = ? where actor_id = ?", Stream.of(row),
                actor.first_name::val,
                actor.last_name::val,
                actor.actor_id::val);


        //Need to work on this.
        queryExecutor.table(Actor.class).set(new Column[]{actor.first_name, actor.last_name}).where( eq(actor.actor_id) + " AND " + eq(actor.last_update) ).on(Stream.of(row));


        /*List rows = new ArrayList();
        rows.add(row);

        queryExecutor.execute("update actor set first_name = ?, last_name = ? where actor_id = ?", rows.stream(), actor.first_name, actor.last_name, actor.actor_id);*/
    }

    private static <T> String eq(Column<T> actor_id) {
        return null;
    }


    @FunctionalInterface
    public interface PreparedStatementSetter<T> {
        void set(PreparedStatement preparedStatement, Integer i, T t) throws SQLException;
    }


    public static class QueryExecutor {

        private Connection connection;

        private Map<Class, TypeInfoHolder> functionMap = new HashMap(10);
        private Map<Class<String>, Integer> sqlTypes = new HashMap<Class<String>, Integer>(10);

        private String table;
        private Column<?> setClause[];

        public QueryExecutor table(Class<Actor> actor) {
            table = Table.init(actor).getTablename();
            return this;
        }

        public <T> QueryExecutor set(Column<T>[] columns){
            setClause = columns;
            return this;
        }

        public QueryExecutor where(String s) {
            return this;
        }


        public <T> void on(Stream<T> row) {

        }

        private static class TypeInfoHolder<T> {
            private PreparedStatementSetter<T> preparedStatementSetter;
            private int type;

            public TypeInfoHolder(PreparedStatementSetter preparedStatementSetter, int type) {
                this.preparedStatementSetter = preparedStatementSetter;
                this.type = type;
            }
        }

        {
            Function<Date, java.sql.Date> dateConverter = date -> new java.sql.Date(date.getTime());

            functionMap.put(String.class, new TypeInfoHolder((PreparedStatementSetter<String>) PreparedStatement::setString, Types.VARCHAR));
            functionMap.put(Integer.class, new TypeInfoHolder((PreparedStatementSetter<Integer>) PreparedStatement::setInt, Types.INTEGER));
            functionMap.put(Long.class, new TypeInfoHolder((PreparedStatementSetter<Long>) PreparedStatement::setLong, Types.NUMERIC));

            PreparedStatementSetter<Date> datePreparedStatementSetter = (preparedStatement, i, date) -> preparedStatement.setDate(i, dateConverter.apply(date));
            functionMap.put(Date.class, new TypeInfoHolder(datePreparedStatementSetter, Types.DATE));

            functionMap.put(Timestamp.class, new TypeInfoHolder((PreparedStatementSetter<Timestamp>) PreparedStatement::setTimestamp, Types.TIMESTAMP));
            functionMap.put(Double.class, new TypeInfoHolder((PreparedStatementSetter<Double>) PreparedStatement::setDouble, Types.DOUBLE));
            functionMap.put(Float.class, new TypeInfoHolder((PreparedStatementSetter<Float>) PreparedStatement::setFloat, Types.FLOAT));

            sqlTypes.put(String.class, Types.VARCHAR);
        }


        public QueryExecutor(Connection connection) {
            this.connection = connection;
        }

        public <T, R> void execute(String query, Stream<T> stream, Function<T, R>... functions) {

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                stream.forEach(t -> {
                    try {
                        for (int i = 0, cnt = 1; i < functions.length; i++, cnt++) {
                            R r = functions[i].apply(t);

                            if (r != null) {
                                functionMap.get(r.getClass()).preparedStatementSetter.set(ps, cnt, r);
                            } else {
                                //set null in prepared statement
                                ps.setNull(cnt, Types.JAVA_OBJECT);
                            }

                        }
                        ps.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                });

                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void execute(String s, Stream<Row> rows, Column... values) {


            PreparedStatement ps = null;

            rows.forEach(row -> {
                try {
                    for (int i = 0, cnt = 1; i < values.length; i++, cnt++) {
                        Object o = values[i].val(row);

                        if (o != null) {
                            functionMap.get(o.getClass()).preparedStatementSetter.set(ps, cnt, o);
                        } else {
                            //set null in prepared statement
                            ps.setNull(cnt, functionMap.get(values[i].getType()).type);
                        }
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
