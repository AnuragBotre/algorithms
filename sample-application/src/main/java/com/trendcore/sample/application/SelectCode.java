package com.trendcore.sample.application;

import com.trendcore.HikariDataSource;
import com.trendcore.SelectStream;
import com.trendcore.sql.Row;

import javax.sql.DataSource;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SelectCode {

    public Supplier<Stream<Row>> executeMysql(String sql, Object... args) {
        return () -> {
            DataSource dataSource = HikariDataSource.get().getDataSource();
            SelectStream select = new SelectStream(dataSource);
            return select.stream(sql, args);
        };
    }

    public Stream<Row> executeOracle(String sql, Object... args){
        DataSource dataSource = HikariDataSource.get().getDataSource();
        SelectStream select = new SelectStream(dataSource);
        return select.stream(sql, args);
    }

}
