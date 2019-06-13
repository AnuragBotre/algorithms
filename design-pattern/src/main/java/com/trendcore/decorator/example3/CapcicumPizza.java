package com.trendcore.decorator.example3;

public class CapcicumPizza extends PizzaDecorator{

    public CapcicumPizza(Pizza originalPizza) {
        super(originalPizza);
    }

    @Override
    public double getCost() {
        return super.getCost() + 4;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Capcicum Pizza.";
    }
}
