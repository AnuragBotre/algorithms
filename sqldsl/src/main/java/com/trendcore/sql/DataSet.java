package com.trendcore.sql;

import java.lang.reflect.Field;
import java.util.*;

public class DataSet<T> {

    private Class<T> tableClass;



    private Map<Field,Integer> fields;

    public DataSet(Class<T> t) {
        this.tableClass = t;


        Field[] f = Person.class.getFields();
        fields = new HashMap(f.length);


        Seq i = new Seq();
        Arrays.asList(Person.class.getFields())
                .forEach(field -> {
                    fields.put(field, i.next());
                });
    }

    public void print(){
        fields.forEach((o, o2) -> System.out.println(o + " " + o2));
    }


    public DataSet next() {
        return this;
    }



}
