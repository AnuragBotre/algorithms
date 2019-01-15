package com.trendcore.functional;

import java.sql.Connection;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Transaction<I> {

    <I> Transaction<I> perform(Function<Connection,? extends I> transaction);

    <R> Transaction<R> afterCommit(Consumer<? super I> args);

    <I> Transaction<I> afterRollback(Consumer<Exception> consumer);

    <T> Transaction<T> _finally(Runnable action);

    void execute();

}
