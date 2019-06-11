package com.trendcore.bridge.example2;

public class ClientForExample2 {

    public static void main(String[] args) {
        Color red = new Red();
        System.out.println("with Red Color..");
        drawShapeWithColor(red);

        System.out.println();

        System.out.println("with Blue Color..");
        Color blue = new Blue();
        drawShapeWithColor(blue);
    }

    private static void drawShapeWithColor(Color red) {
        Shape rectangle = new Rectangle(red);
        Shape square = new Square(red);

        rectangle.draw();
        square.draw();
    }

}
