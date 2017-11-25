package com.trendcore.dao.sql;

import java.util.Map;

/**
 * Created by Anurag on 12/21/2016.
 */
public class Table {

    private String tablename;

    Map<String, Object> values;

    public Table(String tablename) {
        this.tablename = tablename;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public static void main(String[] args) {
        Table t = new Table("Users");

        t.set("id",1);
        t.set("firstname","Anurag");
    }

    private void set(String id, Object object) {
        values.put(id,object);
    }

}
