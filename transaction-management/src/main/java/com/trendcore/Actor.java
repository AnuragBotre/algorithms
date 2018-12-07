package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Seq;
import com.trendcore.sql.Table;

import java.util.Date;
import java.util.List;

public class Actor implements Table {

    public static String name = "Actor";

    public static Column<Integer> ID;
    public static Column<String> NAME;
    public static Column<Date> BIRTHDATE;
    public static Column<Integer> USER_DETAILS;

    public static Seq seq = new Seq();

    private static TableDescriptor tableDescriptor = new TableDescriptor();

    private Row row;

    static {
        Class currentClass = Actor.class;
        Table.init(currentClass);
    }

    public Actor() {
        this.row = new Tuple(seq.val());
    }

    @Override
    public String getTableName() {
        return name;
    }

    //@Override
    public Row getRow() {
        return row;
    }

    @Override
    public List<Column<?>> getColumns() {
        return null;
    }
}
