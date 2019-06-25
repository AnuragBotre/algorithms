package com.trendcore.strategy.example2;

public class ToyDog implements Dog{

    @Override
    public void bark() {
        System.out.println("Make noise");
    }

    @Override
    public void eat() {
        //do nothing
    }
}
