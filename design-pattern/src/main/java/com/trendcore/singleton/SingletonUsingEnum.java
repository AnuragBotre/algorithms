package com.trendcore.singleton;

/**
 * Created by Anurag
 */
public enum SingletonUsingEnum {
    INSTANCE;

    private String a;

    public static SingletonUsingEnum getInstance() {
        return INSTANCE;
    }

    public void method1(){
        System.out.println("Inside method1-"+a);
    }
}
