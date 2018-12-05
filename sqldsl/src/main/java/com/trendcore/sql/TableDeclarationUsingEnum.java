package com.trendcore.sql;

import java.util.ArrayList;

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
    public Object[] getRow() {
        return new Object[0];
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
