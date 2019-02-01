package com.trendcore.sample.application;

import com.trendcore.HikariDataSource;
import com.trendcore.SelectStream;
import com.trendcore.sql.Row;

import javax.sql.DataSource;
import java.util.stream.Stream;

public class SelectCode {

    public Stream<Row> execute(String sql, Object... args){
        DataSource dataSource = HikariDataSource.get().getDataSource();
        SelectStream select = new SelectStream(dataSource);
        return select.stream(sql, args);
    }

}
