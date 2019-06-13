package com.trendcore.decorator.example3;

public abstract class PizzaDecorator implements Pizza{

    private Pizza originalPizza;

    public PizzaDecorator(Pizza originalPizza) {
        this.originalPizza = originalPizza;
    }

    @Override
    public double getCost() {
        return originalPizza.getCost();
    }

    @Override
    public String getDescription() {
        return originalPizza.getDescription();
    }
}
