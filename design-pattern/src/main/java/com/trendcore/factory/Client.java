package com.trendcore.factory;

public class Client {

    public static void main(String[] args) {
        AbstractFactory<Animal> animal = FactoryProvider.getFactory("Animal");
        Animal duck = animal.create("duck");
        System.out.println(duck.getAnimal());
        System.out.println(duck.makeSound());

        AbstractFactory<Color> factory = FactoryProvider.getFactory("");
        Color blue = factory.create("blue");
        System.out.println(blue.getColor());
        blue.fill();
    }

}
