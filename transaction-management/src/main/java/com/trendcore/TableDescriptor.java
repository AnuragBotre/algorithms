package com.trendcore;

import com.trendcore.sql.Column;

import java.util.ArrayList;
import java.util.List;

public class TableDescriptor {

    private List<Column<?>> list = new ArrayList();

    private String tablename = "";

    public void add(Column<?> c){
        list.add(c);
    }

    public List<Column<?>> getColumns() {
        return list;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
}
