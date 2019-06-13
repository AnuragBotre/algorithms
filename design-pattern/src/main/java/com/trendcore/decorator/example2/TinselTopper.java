package com.trendcore.decorator.example2;

public class TinselTopper extends ChristmasTreeDecorator {

    public TinselTopper(ChristmasTree christmasTree) {
        super(christmasTree);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Decorate with Tinsel Topper";
    }
}
