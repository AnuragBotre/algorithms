package com.trendcore.dao.sql;

import com.trendcore.dao.Column;

import java.util.Map;

/**
 * Created by Anurag on 12/19/2016.
 */
public interface TableRow {

    Map<Column<?>,Object> getColumns();

    String getName();
}
