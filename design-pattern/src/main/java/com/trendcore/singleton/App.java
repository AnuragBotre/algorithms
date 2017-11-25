package com.trendcore.singleton;

/**
 * Created by Anurag
 */
public class App {

    public static void main(String[] args) throws CloneNotSupportedException {
        SingletonClass instance = SingletonClass.getInstance();

        System.out.println(instance.hashCode());
        System.out.println(instance.clone().hashCode());

    }

}
