package com.trendcore.decorator.example2;

public class ClientForDecoratorExample2 {

    public static void main(String[] args) {
        ChristmasTree christmasTree = new ChristmasTreeImpl();

        ChristmasTree bubbleLightChristmasTree = new BubbleLightDecorator(christmasTree);
        System.out.println(bubbleLightChristmasTree.getDescription());

        ChristmasTree bubbleLightWithTinselChristmasTree = new TinselTopper(bubbleLightChristmasTree);
        System.out.println(bubbleLightWithTinselChristmasTree.getDescription());
    }

}
