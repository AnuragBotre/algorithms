package com.trendcore.singleton;

/**
 * Created by Anurag
 */
public class SingletonUsingEnumApp {

    public static void main(String[] args) {
        SingletonUsingEnum instance = SingletonUsingEnum.getInstance();

        instance.method1();
    }

}
