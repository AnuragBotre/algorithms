package com.trendcore;

import com.trendcore.exception.SystemException;

public class CodeExecutor {

    public static <T> ExceptionHandler<T> execute(Runnable runnable,Class<T> t) {
        try {
            runnable.run();
            return new NoExceptionHandler();
        }catch (Exception e) {
            return (ExceptionHandler<T>) new SystemExceptionHandler(SystemException.wrap(e));
        }
    }
}
