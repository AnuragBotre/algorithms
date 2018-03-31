package com.trendcore.sql;

public interface Row {

    <T> T get(int index);
}
