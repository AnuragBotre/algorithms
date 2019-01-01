package com.trendcore;

import com.trendcore.exception.ErrorCode;

public class DatabaseErrorCode implements ErrorCode {

    public static final String DATA_ACCESS_EXCEPTION = "DATA_ACCESS_EXCEPTION";
    public static final String INVALID_QUERY = "INVALID_QUERY";
    public static final String INVALID_PARAMS_FOR_PREPARED_STATEMENT = "INVALID_PARAMS_FOR_PREPARED_STATEMENT";
    public static final String QUERY_EXECUTION = "QUERY_EXECUTION";
    public static final String DATASOURCE_NO_CONNECTION = "DATASOURCE_NO_CONNECTION";

    public static final String CATEGORY = "DatabaseErrorCode";

    String s;

    public DatabaseErrorCode(String s) {
        this.s = s;
    }

    @Override
    public String getErrorCode() {
        return s;
    }
}
