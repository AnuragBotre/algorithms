package com.trendcore.errorcode;

import com.trendcore.exception.ErrorCode;

/**
 * Created by Anurag on 12/25/2016.
 */
public class MySQLErrorCode implements ErrorCode{

    private int errorCode;

    public MySQLErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public int getNumber() {
        return errorCode;
    }
}
