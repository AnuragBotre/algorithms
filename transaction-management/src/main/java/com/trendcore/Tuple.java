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
    public Object get(int index) {
        return tuple[index];
    }
}
