package com.trendcore;

import com.trendcore.exception.SystemException;

import java.util.function.Consumer;

public class SystemExceptionHandler implements ExceptionHandler<SystemException>{

    SystemException systemException;

    public SystemExceptionHandler(SystemException e) {
        systemException = e;
    }

    @Override
    public void handle(Consumer<SystemException> consumer) {
        System.out.println(systemException.getErrorCategory() + "  " + systemException.getErrorCode().getErrorCode());
        consumer.accept(systemException);
    }
}
