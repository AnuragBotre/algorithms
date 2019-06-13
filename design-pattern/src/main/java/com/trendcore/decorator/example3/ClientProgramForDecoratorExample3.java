package com.trendcore.decorator.example3;

public class ClientProgramForDecoratorExample3 {

    public static void main(String[] args) {
        Pizza pizza = new BasicPizza();
        Pizza cheeseBurstPizza = new CheeseBurstPizza(pizza);
        System.out.println(cheeseBurstPizza.getDescription() + " " + cheeseBurstPizza.getCost());


        //Cheese burst with capsicum

        Pizza capsicumPizza = new CapcicumPizza(pizza);
        Pizza cheeseBurstCapsicumPizza = new CheeseBurstPizza(capsicumPizza);

        System.out.println(cheeseBurstCapsicumPizza.getCost() + " " + cheeseBurstCapsicumPizza.getDescription());


    }

}
