package com.trendcore.memento;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundShape extends BaseShape {

    private List<Shape> children = new ArrayList<>();

    public CompoundShape(Shape... shapes) {
        super(0, 0, Color.BLACK);
        add(shapes);
    }

    public void add(Shape... shapes) {
        children.addAll(Arrays.asList(shapes));
    }

    public void add(Shape shape) {
        children.add(shape);
    }

    public void remove(Shape child) {
        children.remove(child);
    }

    public void remove(Shape... shapes) {
        children.removeAll(Arrays.asList(shapes));
    }

    public void clear() {
        children.clear();
    }

    public int getX(){
        if(children.isEmpty()){
            return 0;
        }

        int x = children.get(0).getX();

        for(int i = 1; i < children.size() ; i++){
            if(x > children.get(i).getX()){
                x = children.get(i).getX();
            }
        }

        return x;
    }

    public int getY(){
        if(children.isEmpty()){
            return 0;
        }

        int y = children.get(0).getY();

        for(int i = 1; i < children.size() ; i++){
            if(y > children.get(i).getY()){
                y = children.get(i).getY();
            }
        }

        return y;
    }

    public int getWidth(){
        int width = getX();
        int maxWidth = 0;

        for(Shape child : children){
            int childRelativeX = child.getX() - width;
            int childWidth = childRelativeX + child.getWidth();

            if(childWidth > maxWidth){
                maxWidth = childWidth;
            }
        }

        return maxWidth;
    }


    public int getHeight(){
        int maxHeight = 0;
        int y = getY();
        for (Shape child : children) {
            int childRelativeY = child.getY() - y;
            int childHeight = childRelativeY + child.getHeight();
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }
        return maxHeight;
    }


    public List<Shape> getSelected() {
        List<Shape> selected = new ArrayList<>();
        for (Shape child : children) {
            if (child.isSelected()) {
                selected.add(child);
            }
        }
        return selected;
    }

    public Shape getChildAt(int x, int y) {
        for (Shape child : children) {
            if (child.isInsideBounds(x, y)) {
                return child;
            }
        }
        return null;
    }
}
