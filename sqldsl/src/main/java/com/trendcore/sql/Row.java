package com.trendcore.sql;

public interface Row<V extends Table> {

    <T> T get(int index);

    <T> void set(int index, T t);
}
