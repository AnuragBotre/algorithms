package com.trendcore.sample.application;

import java.util.function.Function;
import java.util.function.Supplier;

public class Database {

    private String sql;

    private Object args[];

    public <T,R> void select(Function<T, R> function){

    }

    public void select(String sql, Object... args) {
        this.sql = sql;
        this.args = args;
    }


}
