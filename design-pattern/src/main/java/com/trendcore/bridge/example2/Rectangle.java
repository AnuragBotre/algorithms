package com.trendcore.bridge.example2;

public class Rectangle implements Shape{

    private Color color;

    public Rectangle(Color color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle..");
        color.fill();
    }
}
