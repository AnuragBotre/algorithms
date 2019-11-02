package com.trendcore.dysfunctional.laziness;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ExecuteOnceSupplier<T> implements Supplier {

    private Supplier<T> originalSupplier;

    private AtomicBoolean executed = new AtomicBoolean(false);

    public ExecuteOnceSupplier(Supplier<T> supplier) {

        originalSupplier = () -> {
            if (!executed.get()) {
                System.out.println("Initializing !");
                executed.getAndSet(true);
            }
            return supplier.get();
        };
    }

    @Override
    public Object get() {
        return originalSupplier.get();
    }
}
