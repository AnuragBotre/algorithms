package com.trendcore.spring.beans;

public class A {

    private static final A obj = new A();

    public static A getA() {
        System.out.println("factory method ");
        return obj;
    }

}
