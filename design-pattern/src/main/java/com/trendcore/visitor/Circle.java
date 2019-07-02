package com.trendcore.visitor;

public class Circle implements Shape {

    @Override
    public void move(int x, int y) {
        System.out.println("move Circle to x = " + x + " y = " + y);
    }

    @Override
    public void draw() {
        System.out.println("Draw circle.");
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
