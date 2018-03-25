package com.trendcore.sql;

public enum Address implements SQLField {

    ID(SQLType.INTEGER),
    STREET(SQLType.STRING);

    private SQLType sqlType;

    Address(SQLType sqlType) {
        this.sqlType = sqlType;
    }

    @Override
    public SQLType getSQLtype() {
        return sqlType;
    }
}
