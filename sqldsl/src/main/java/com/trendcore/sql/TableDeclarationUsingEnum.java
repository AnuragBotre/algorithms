package com.trendcore.sql;

import java.util.ArrayList;

public enum TableDeclarationUsingEnum {

    ID(Integer.class),
    ADDRESS1(String.class),
    ADDRESS(String.class);

    Class type;


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
