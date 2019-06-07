package com.trendcore.factory;

public class FactoryProvider {

    public static AbstractFactory getFactory(String choice){
        if(choice.equals("Animal")){
            return new AnimalFactory();
        }else {
            return new ColorFactory();
        }
    }

}
