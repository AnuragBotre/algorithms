package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

import java.util.Date;
import java.util.List;

public class EnhancedActor implements Table {

    public static String name = "Actor";

    public static Column<Integer> ID;
    public static Column<String> NAME;
    public static Column<Date> BIRTHDATE;
    public static Column<Integer> USER_DETAILS;

    static {
        Class currentClass = EnhancedActor.class;
        Table.init(currentClass);
    }

    private Row row;

    public EnhancedActor() {
        //this.row = newRow(this.getClass());
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
