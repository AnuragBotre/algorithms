package com.trendcore.visitor;

public class Circle implements Shape {

    String id;

    int x;

    int y;

    int radius;

    public Circle(String i, int i1, int i2, int i3) {
        this.id = i;
        this.x = i1;
        this.y = i2;
        this.radius = i3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

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
        return visitor.visitCircle(this);
    }
}
