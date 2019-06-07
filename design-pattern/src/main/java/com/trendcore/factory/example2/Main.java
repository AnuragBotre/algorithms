package com.trendcore.factory.example2;

public class Main {

    public static void main(String[] args) {
        AbstractFactory ember = FactoryProvider.getToolkit("Ember");

        System.out.println(ember.getCpu().printVersion());
    }

}
