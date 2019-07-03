package com.trendcore.visitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundShape implements Shape {

    private String id;

    private List<Shape> children = new ArrayList<>();

    public CompoundShape(String i) {
        this.id = i;
    }

    public List<Shape> getChildren() {
        return children;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void draw() {

    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCompoundGraphic(this);
    }

    public void add(Shape shape){
        children.add(shape);
    }
}
