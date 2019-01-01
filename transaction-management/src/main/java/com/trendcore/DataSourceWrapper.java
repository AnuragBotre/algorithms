package com.trendcore;

import com.trendcore.exception.SystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceWrapper {

    public static Connection getConnection(DataSource dataSource){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw SystemException.wrap(e,() -> DatabaseErrorCode.DATASOURCE_NO_CONNECTION , DatabaseErrorCode.CATEGORY);
        }
    }

}
