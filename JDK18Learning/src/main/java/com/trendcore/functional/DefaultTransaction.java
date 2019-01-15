package com.trendcore.functional;

import java.sql.Connection;
import java.util.function.Consumer;
import java.util.function.Function;

public class DefaultTransaction<E> implements Transaction<E> {

    @Override
    public <I> Transaction<I> perform(Function<Connection, ? extends I> transaction) {
        return null;
    }

    @Override
    public <R> Transaction<R> afterCommit(Consumer<? super E> args) {
        return null;
    }

    @Override
    public <T> Transaction<T> afterRollback(Consumer<Exception> consumer) {
        return null;
    }

    @Override
    public <T> Transaction<T> _finally(Runnable action) {
        return null;
    }


    @Override
    public void execute() {

    }
}
