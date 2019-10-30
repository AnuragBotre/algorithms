package com.trendcore.dysfunctional.laziness;

import java.util.function.Supplier;

public class ExecuteOnceSupplier<T> implements Supplier {

    private Supplier<T> originalSupplier;

    boolean executed;

    public ExecuteOnceSupplier(Supplier<T> supplier) {

        originalSupplier = () -> {
            if (!executed) {
                System.out.println("Intializing !");
                executed = true;
                originalSupplier = supplier;
            }
            return supplier.get();
        };
    }

    @Override
    public Object get() {
        return originalSupplier.get();
    }
}
