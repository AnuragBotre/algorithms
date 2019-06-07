package com.trendcore.factory;

public class Black implements Color {

    @Override
    public String getColor() {
        return "Black";
    }

    @Override
    public void fill() {
        System.out.println("Filling Black Color");
    }
}
