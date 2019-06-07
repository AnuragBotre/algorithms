package com.trendcore.factory.example2;

public class FactoryProvider {

    static AbstractFactory getToolkit(String choice){
        switch (choice){
            case "Ember" :
                return new EmberToolkit();

            case "Engilo":
                return new EngiloToolkit();

        }

        throw new RuntimeException("Invalid choice");
    }

}
