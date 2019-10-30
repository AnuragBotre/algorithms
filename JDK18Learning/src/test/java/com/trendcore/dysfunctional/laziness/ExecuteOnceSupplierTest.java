package com.trendcore.dysfunctional.laziness;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExecuteOnceSupplierTest {

    @Test
    public void executeOnceSupplierTest() {
        ExecuteOnceSupplier executeOnceSupplier = new ExecuteOnceSupplier(() -> {
            System.out.println("Executed");
            return 0;
        });

        executeOnceSupplier.get();
        executeOnceSupplier.get();
        executeOnceSupplier.get();
    }
}