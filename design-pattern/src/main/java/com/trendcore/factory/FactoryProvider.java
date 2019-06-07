package com.trendcore.factory;

public class FactoryProvider {

    public static AbstractFactory getFactory(){
        return new AbstractFactory();
    }

}
