package com.trendcore.visitor;

public class Rectangle implements Shape{

    @Override
    public void move(int x, int y) {
        System.out.println("move Rectangle to x = " + x + " y = " + y);
    }

    @Override
    public void draw() {

    }

    @Override
    public String accept(Visitor visitor) {
        return null;
    }
}
