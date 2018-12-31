package com.trendcore;

import com.trendcore.exception.SystemException;

import java.util.function.Consumer;

public interface ExceptionHandler<T> {

    void handle(Consumer<T> consumer);

}
