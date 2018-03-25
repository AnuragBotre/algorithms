package com.trendcore.sql;

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

        DataSet<Student> d = new DataSet(Student.class);

        DataSet<Student>.Row list = d.list(0);


    }

}
