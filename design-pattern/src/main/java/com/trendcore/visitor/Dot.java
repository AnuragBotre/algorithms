package com.trendcore.visitor;

public class Dot implements Shape {

    @Override
    public void move(int x, int y) {
        System.out.println("move dot to x = " + x + " y = " + y);
    }

    @Override
    public void draw() {
        System.out.println("Draw dot.");
    }

    @Override
    public String accept(Visitor visitor) {
        return null;
    }
}
