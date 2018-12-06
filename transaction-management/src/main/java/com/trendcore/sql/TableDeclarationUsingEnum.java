package com.trendcore.sql;

import java.util.List;

public enum TableDeclarationUsingEnum implements Table{

    ID(Integer.class),
    ADDRESS1(String.class),
    ADDRESS(String.class);

    public static String name = "Address";

    Class type;

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Row getRow() {
        return null;
    }

    @Override
    public List<Column<?>> getColumns() {
        return null;
    }

    @Override
    public <T> void val(Column<T> id, T t) {

    }

    @Override
    public <T> T val(Column<T> id) {
        return null;
    }

    TableDeclarationUsingEnum(Class<?> t) {
        type = t;
    }

    public Class getType() {
        return type;
    }

    public static void main(String[] args) {
        TableDeclarationUsingEnum[] values = TableDeclarationUsingEnum.values();
        for (TableDeclarationUsingEnum v :
                values) {
            System.out.println(v+" " + v.type);
        }
    }


}
