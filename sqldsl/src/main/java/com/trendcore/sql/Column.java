package com.trendcore.sql;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Column<T> {

    private Class<T> type;
    private String name;

    private int index;

    public Column() {
    }

    public Column(Class<T> type) {
        this.type =  type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //    public T cast(Object obj) {
//        return obj == null ? null : type.cast(obj);
//    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T val(Row row) {
        return (T) row.get(index);
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
