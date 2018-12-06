package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

public class Tuple implements Row {

    Object tuple[];

    public Tuple(int columnCount) {
        tuple = new Object[columnCount];
    }

    @Override
    public void set(Column column, Object o) {
        tuple[column.getIndex()] = o;
    }

    @Override
    public Object get(Column id) {
        return tuple[id.getIndex()];
    }
}
