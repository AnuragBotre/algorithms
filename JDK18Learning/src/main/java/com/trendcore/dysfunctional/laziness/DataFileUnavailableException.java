package com.trendcore.dysfunctional.laziness;

public class DataFileUnavailableException extends RuntimeException {

    public DataFileUnavailableException(Exception e) {
        super(e);
    }
}
