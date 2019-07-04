package com.trendcore.memento;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundShape extends BaseShape {

    private List<Shape> childrens = new ArrayList<>();

    public CompoundShape(Shape... shapes) {
        super(0, 0, Color.BLACK);
        add(shapes);
    }

    public void add(Shape... shapes) {
        childrens.addAll(Arrays.asList(shapes));
    }

    public void add(Shape shape) {
        childrens.add(shape);
    }

    public void remove(Shape child) {
        childrens.remove(child);
    }

    public void remove(Shape... shapes) {
        childrens.removeAll(Arrays.asList(shapes));
    }

    public void clear() {
        childrens.clear();
    }


}
