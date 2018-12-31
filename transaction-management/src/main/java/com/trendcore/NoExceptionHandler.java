package com.trendcore;

import com.trendcore.exception.SystemException;

import java.util.function.Consumer;

public class NoExceptionHandler implements ExceptionHandler{

    public NoExceptionHandler() {
    }

    @Override
    public void handle(Consumer consumer) {

    }
}
