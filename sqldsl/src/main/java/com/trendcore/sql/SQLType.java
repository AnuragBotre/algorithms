package com.trendcore.sql;

public enum SQLType {
    INTEGER(Integer.class),
    STRING(String.class);

    private Class clazz;

    SQLType(Class clazz) {
        this.clazz = clazz;
    }

    public Class getType(){
        return clazz;
    }
}
