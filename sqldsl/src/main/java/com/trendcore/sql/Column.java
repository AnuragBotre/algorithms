package com.trendcore.sql;

public class Column<T> {

//    private Class<T> type;
    private String name;

    public Column(String name) {
//        this.type = type;
        this.name = name;
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

}
