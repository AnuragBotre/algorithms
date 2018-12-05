package com.trendcore.sql;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public interface Table {

    static Seq init(Class aClass, Seq seq, List<Column<?>> colums) {

        Arrays.asList(aClass.getDeclaredFields()).stream().filter(field -> field.getType().isAssignableFrom(Column.class))
                .forEach(field -> {
                    try {
                        Column c = new Column();
                        c.setName(field.getName());
                        c.setType((Class) ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);
                        c.setIndex(seq.next());
                        field.set(null,c);
                        colums.add(c);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return seq;
    }

    String getTableName();

    default void insert(Connection connection) {
        approach1();
    }

    Object[] getRow();

    default void approach1() {


    }

    List<Column<?>> getColumns();
}
