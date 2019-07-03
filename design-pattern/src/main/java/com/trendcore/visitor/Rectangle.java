package com.trendcore.visitor;

public class Rectangle implements Shape{

    String id;

    int x;

    int y;

    int width;

    int height;

    public Rectangle(String s, int i, int i1, int i2, int i3) {
        this.id = s;
        this.x = i;
        this.y = i1;
        this.width = i2;
        this.height = i3;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void move(int x, int y) {
        System.out.println("move Rectangle to x = " + x + " y = " + y);
    }

    @Override
    public void draw() {

    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitRectangle(this);
    }
}
