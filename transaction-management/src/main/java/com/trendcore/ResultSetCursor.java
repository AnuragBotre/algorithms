package com.trendcore;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetCursor {

    private ResultSetMetaData metaData;
    private ResultSet resultSet;

    public ResultSetCursor(ResultSet rs) throws SQLException {
        resultSet = rs;
        metaData = resultSet.getMetaData();
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
