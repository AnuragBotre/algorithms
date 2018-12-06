package com.trendcore.sql;

import java.util.Date;

public class Person implements SQLField {

    public static String firstname = "";

    public static String lastname;

    public static SQLType id = SQLType.INTEGER;


    @Override
    public SQLType getSQLtype() {
        return null;
    }

    public Person() {


    }



    public static void main(String[] args) throws NoSuchFieldException {



        /*Row<Student> s = d.get(0);

        Integer integer = s.get(Student.ID);
        Date date = s.get(Student.BIRTHDATE);
        String s1 = s.get(Student.NAME);*/


    }

}
