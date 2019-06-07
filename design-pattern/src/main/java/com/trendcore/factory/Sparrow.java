package com.trendcore.factory;

public class Sparrow implements Animal {

    @Override
    public String getAnimal() {
        return "Sparrow";
    }

    @Override
    public String makeSound() {
        return "Squeek";
    }
}
