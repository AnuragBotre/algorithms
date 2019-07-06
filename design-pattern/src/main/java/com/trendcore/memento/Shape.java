package com.trendcore.memento;

import java.awt.*;

public interface Shape {

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    void drag();

    void moveTo(int x, int y);

    void moveBy(int x, int y);

    void drop();

    boolean isInsideBounds(int x, int y);

    Color getColor();

    void setColor(Color color);

    void select();

    void unSelect();

    boolean isSelected();

    void enableSelectionStyle(Graphics graphics);

    void disableSelectionStyle(Graphics graphics);

    void paint(Graphics graphics);
}
