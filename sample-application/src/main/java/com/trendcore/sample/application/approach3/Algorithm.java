package com.trendcore.sample.application.approach3;

import com.trendcore.HikariDataSource;
import com.trendcore.SelectStream;
import com.trendcore.exception.SystemException;
import com.trendcore.sql.Row;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Algorithm<T,R> {

    private T input;

    private String type = "mysql";

    private Function<T,R> map;

    private Function<T,DatabaseContext> databaseContextSupplier;

    public Algorithm(T t) {
        input = t;
    }

    public static class QueryContext{

        private String query;
        private Object args[];

        public QueryContext(String query,Object... args){
            this.query = query;
            this.args = args;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }
    }

    public static class DatabaseContext{
        public Supplier<QueryContext> mysql;
        public Supplier<QueryContext> oracle;

        public DatabaseContext mysql(Supplier<QueryContext> mysql) {
            this.mysql = mysql;
            return this;
        }

        public DatabaseContext oracle(Supplier<QueryContext> oracle) {
            this.oracle = oracle;
            return this;
        }
    }

    public <I> Algorithm validate(Function<T,R> validate) {
        return this;
    }

    public <I,V> Algorithm<I,V> map(Function<T,I> map) {
        this.map = (Function<T, R>) map;
        return (Algorithm<I, V>) this;
    }

    public <I> Algorithm transaction(Function<T,R> transaction) {
        return this;
    }

    public <I,V> Algorithm<I,V> fetch(Function<I,DatabaseContext> databaseContextSupplier) {
        this.databaseContextSupplier = (Function<T, DatabaseContext>) databaseContextSupplier;
        return (Algorithm<I, V>) this;
    }

    public Stream<Row> execute() {
        Object apply = map.apply(input);
        DataSource dataSource = HikariDataSource.get().getDataSource();
        SelectStream select = new SelectStream(dataSource);

        Algorithm.QueryContext queryContext = null;
        DatabaseContext databaseContext = databaseContextSupplier.apply((T) apply);
        if (type.equals("mysql")) {
            queryContext = databaseContext.mysql.get();
        } else if(type.equals("oracle")) {
            queryContext = databaseContext.oracle.get();
        }

        if(queryContext == null){
            throw new SystemException("Could not retrieve query context for " + type, () -> "1000");
        }

        return select.stream(queryContext.getQuery(), queryContext.getArgs());
    }

}
