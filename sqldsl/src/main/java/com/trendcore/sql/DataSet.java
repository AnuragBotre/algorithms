package com.trendcore.sql;

import java.lang.reflect.Field;
import java.util.*;

public class DataSet<T> {

    private Class<T> tableClass;

    private List list;

    private Map<Field,Integer> fields;

    public DataSet(Class<T> t) {
        this.tableClass = t;
        list = new ArrayList();

        Field[] f = Person.class.getFields();
        fields = new HashMap(f.length);


        Increment i = new Increment();
        Arrays.asList(Person.class.getFields())
                .forEach(field -> {
                    fields.put(field, i.increment());
                });
    }

    public void print(){
        fields.forEach((o, o2) -> System.out.println(o + " " + o2));
    }


    public DataSet next() {
        return this;
    }

    public Row list(int i) {
        return new Row();
    }

    public class Row{

        Object arr[];

        public <R> R get(Column<R> col) {
            System.out.println(col.name());
            return null;
        }
    }
}
