package com.trendcore.decorator.example3;

public class PepporaniPizza extends PizzaDecorator{

    public PepporaniPizza(Pizza originalPizza) {
        super(originalPizza);
    }

    @Override
    public double getCost() {
        return super.getCost() + 2;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ". Pepperoni pizza.";
    }
}
