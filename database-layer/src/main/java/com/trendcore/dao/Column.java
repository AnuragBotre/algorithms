package com.trendcore.dao;

/**
 * Created by Anurag on 12/17/2016.
 */
public class Column<T> {

    private Class<T> type;
    private String name;

    public Column(String colunameName,Class<T> type) {
        this.type = type;
        this.setName(colunameName);
    }

    public T cast(Object obj) {
        return obj == null ? null : type.cast(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
