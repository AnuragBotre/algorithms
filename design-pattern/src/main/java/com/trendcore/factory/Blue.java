package com.trendcore.factory;

public class Blue implements Color {

    @Override
    public String getColor() {
        return "Blue";
    }

    @Override
    public void fill() {
        System.out.println("Filling Blue Color");
    }
}
