package com.trendcore;

/**
 * Created by Anurag
 */
public class Java7Resouce implements AutoCloseable{

    public Java7Resouce() {
        System.out.println("Creating Java 7 resouce.");
    }

    public void op1() {
        System.out.println("Executing Operation 1");
    }

    public void op2() {
        System.out.println("Executing Operation 2");
    }

    @Override
    public void close() {
        System.out.println("closing resouce");
    }
}
