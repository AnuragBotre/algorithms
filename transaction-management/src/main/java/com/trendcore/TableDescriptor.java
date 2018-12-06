package com.trendcore;

import com.trendcore.sql.Column;

import java.util.ArrayList;
import java.util.List;

public class TableDescriptor {

    private List<Column> list = new ArrayList();

    public void add(Column c){
        list.add(c);
    }

    public List<Column> getColums() {
        return list;
    }
}
