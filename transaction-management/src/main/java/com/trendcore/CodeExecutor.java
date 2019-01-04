package com.trendcore;

import com.trendcore.exception.SystemException;

public class CodeExecutor {

    public static <T> ExceptionHandler<SystemException> execute(Runnable runnable) {
        try {
            runnable.run();
            return new NoExceptionHandler();
        }catch (Exception e) {
            return new SystemExceptionHandler(SystemException.wrap(e));
        }
    }
}
