package com.trendcore.sql;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Student implements Table{

    public static String name = "Student";

    public static Column<Integer> ID;
    public static Column<String> NAME;
    public static Column<Date> BIRTHDATE;
    public static Column<Integer> USER_DETAILS;

    public static List list = new ArrayList();

    Object obj[];

    public <T> void val(Column<T> col,T t) {
        obj[col.getIndex()] = t;
    }

    public <T> T val(Column<T> col) {
        return (T) obj[col.getIndex()];
    }


    public <T> T get(int index) {
        return (T) obj[index];
    }

    public Student() {
        Class currentClass = this.getClass();

        Seq seq = new Seq();
        Arrays.asList(currentClass.getDeclaredFields()).stream().filter(field -> field.getType().isAssignableFrom(Column.class))
                .forEach(field -> {
                    try {
                        Column c = new Column();
                        c.setName(field.getName());
                        c.setType((Class) ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);
                        c.setIndex(seq.next());
                        field.set(null,c);
                        list.add(c);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        obj = new Object[seq.val()];
    }

    @Override
    public List<Column<?>> getColumns() {
        return list;
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Object[] getRow() {
        return obj;
    }

    interface SomeInterface{
        void val();
    }


    public static void main(String[] args) {

        class A implements SomeInterface{
            String abc = "";
            String a = "1";
            String b = "1";

            @Override
            public void val() {

            }
        }

        A a = new A();
        a.a = "2";

        Student s = new Student();

        s.someMethod(a);


        s.val(s.ID,1);
        s.val(s.BIRTHDATE,new Date());
        s.val(s.NAME,"John");


        Integer val = s.val(s.ID);
        Date birthdate = s.val(Student.BIRTHDATE);
        String val1 = s.val(Student.NAME);


        //Integer val2 = s.ID.val(s);

        //System.out.println(val + " " + birthdate + " " + val1 +" - "+ val2);
        System.out.println(val + " " + birthdate + " " + val1);
    }

    private void someMethod(SomeInterface a) {
        a.val();
    }

}
