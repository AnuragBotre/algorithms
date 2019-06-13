package com.trendcore.decorator.example3;

public class BasicPizza implements Pizza{

    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Basic Pizza";
    }
}
