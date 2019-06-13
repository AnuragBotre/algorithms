package com.trendcore.decorator.example3;

public class CheeseBurstPizza extends PizzaDecorator{

    public CheeseBurstPizza(Pizza originalPizza) {
        super(originalPizza);
    }

    @Override
    public double getCost() {
        return super.getCost() + 4;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ". Cheese Burst Pizza.";
    }
}
