package com.trendcore.visitor;

public class Dot implements Shape {

    String id;

    int x;

    int y;

    public Dot(String i, int i1, int i2) {
        this.id = i;
        this.x = i1;
        this.y = i2;
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
        return visitor.visitDot(this);
    }
}
