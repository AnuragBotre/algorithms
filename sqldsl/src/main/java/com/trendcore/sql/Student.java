package com.trendcore.sql;

import java.util.Date;

public class Student {

    public static Column<Integer> ID = new Column<>("ID");
    public static Column<String> NAME = new Column<>("NAME");
    public static Column<Date> BIRTHDATE = new Column<>("BIRTHDATE");


    public static void main(String[] args) {

        DataSet d = new DataSet(Person.class);





    }

}
