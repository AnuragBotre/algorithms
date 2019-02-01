package com.trendcore.functional;

import java.sql.Connection;
import java.util.function.Consumer;
import java.util.function.Function;

public class DefaultTransaction<E> implements Transaction<E> {

    Function<Connection,?> transaction;

    @Override
    public <I> Transaction<I> perform(Function<Connection, ? extends I> transaction) {
        Function<Connection, ? extends I> t;
        this.transaction = transaction;
        return (Transaction<I>) this;
    }

    @Override
    public <R> Transaction<R> afterCommit(Consumer<? super E> args) {
        return (Transaction<R>) this;
    }

    @Override
    public <T> Transaction<T> afterRollback(Consumer<Exception> consumer) {
        return (Transaction<T>) this;
    }

    @Override
    public <T> Transaction<T> _finally(Runnable action) {
        return (Transaction<T>) this;
    }


    @Override
    public void execute() {

        Connection connection = null;

        this.transaction.apply(connection);
    }
}
