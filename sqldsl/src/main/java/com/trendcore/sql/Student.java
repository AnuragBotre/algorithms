package com.trendcore.sql;

import java.util.Arrays;
import java.util.Date;

public class Student{

    public static Column<Integer> ID = new Column<>("ID");
    public static Column<String> NAME = new Column<>("NAME");
    public static Column<Date> BIRTHDATE = new Column<>("BIRTHDATE");

    Object obj[];

    public <T> void val(Column<T> col,T t) {
        obj[col.getIndex()] = t;
    }

    public <T> T val(Column<T> col) {
        return (T) obj[col.getIndex()];
    }



    public Student() {
        Class currentClass = this.getClass();

        Seq seq = new Seq();
        Arrays.asList(currentClass.getFields()).stream().filter(field -> field.getType().isAssignableFrom(Column.class))
                .forEach(field -> {
                    try {
                        Column o = (Column) field.get(this);
                        o.setIndex(seq.increment());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        obj = new Object[seq.val()];
    }

    public static void main(String[] args) {


        Student s = new Student();


        s.val(Student.ID,1);
        s.val(Student.BIRTHDATE,new Date());
        s.val(Student.NAME,"John");


        Integer val = s.val(Student.ID);
        Date birthdate = s.val(Student.BIRTHDATE);
        String val1 = s.val(Student.NAME);

        System.out.println(val + " " + birthdate + " " + val1);
    }

}
