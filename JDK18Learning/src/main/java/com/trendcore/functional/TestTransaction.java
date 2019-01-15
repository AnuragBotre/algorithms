package com.trendcore.functional;

public class TestTransaction {

    public static <R> void main(String[] args) {
        Transaction<R> transaction = new DefaultTransaction();

        transaction
        .perform(connection -> new TestTransaction())
        .afterCommit(testTransaction -> {

        }).afterRollback(e -> {

        })._finally(() -> {

        });
    }

}
