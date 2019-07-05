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

    public int getX(){
        if(childrens.isEmpty()){
            return 0;
        }

        int x = childrens.get(0).getX();

        for(int i = 1 ; i < childrens.size() ; i++){
            if(x > childrens.get(i).getX()){
                x = childrens.get(i).getX();
            }
        }

        return x;
    }

    public int getY(){
        if(childrens.isEmpty()){
            return 0;
        }

        int y = childrens.get(0).getY();

        for(int i = 1 ; i < childrens.size() ; i++){
            if(y > childrens.get(i).getY()){
                y = childrens.get(i).getY();
            }
        }

        return y;
    }

    public int getWidth(){
        int width = getX();
        int maxWidth = 0;

        for(Shape child : childrens){
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
        for (Shape child : childrens) {
            int childRelativeY = child.getY() - y;
            int childHeight = childRelativeY + child.getHeight();
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }
        return maxHeight;
    }


}
