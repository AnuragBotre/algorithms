package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Seq;
import com.trendcore.sql.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Actor implements Table {

    public static String name = "Student";

    public static Column<Integer> ID;
    public static Column<String> NAME;
    public static Column<Date> BIRTHDATE;
    public static Column<Integer> USER_DETAILS;

    public static Seq seq = new Seq();

    private static List<Column<?>> colums = new ArrayList<>();

    private Row row;

    static
    {
        Class currentClass = Actor.class;
        Table.init(currentClass,seq,colums);
    }

    public Actor() {
        this.row = new Tuple(seq.val());
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Object[] getRow() {
        return new Object[0];
    }

    @Override
    public List<Column<?>> getColumns() {
        return colums;
    }
}
