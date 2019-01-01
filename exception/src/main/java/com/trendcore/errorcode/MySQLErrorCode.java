package com.trendcore.errorcode;

import com.trendcore.exception.ErrorCode;

/**
 * Created by Anurag on 12/25/2016.
 */
public class MySQLErrorCode implements ErrorCode{

    private String errorCode;

    public MySQLErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
