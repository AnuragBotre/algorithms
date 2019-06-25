package com.trendcore.strategy.example2;

public class Labroader implements Dog{

    @Override
    public void bark() {
        System.out.println("Growl!");
    }

    @Override
    public void eat() {
        System.out.println("Food.");
    }
}
