package com.trendcore.factory;

public class AnimalFactory implements AbstractFactory<Animal> {

    @Override
    public Animal create(String choice) {
        if (choice.equals("duck")) {
            return new Duck();
        } else if (choice.equals("sparrow")) {
            return new Sparrow();
        }
        return null;
    }
}
