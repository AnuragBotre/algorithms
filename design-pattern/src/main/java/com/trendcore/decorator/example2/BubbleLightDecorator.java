package com.trendcore.decorator.example2;

public class BubbleLightDecorator extends ChristmasTreeDecorator {

    public BubbleLightDecorator(ChristmasTree christmasTree) {
        super(christmasTree);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Decorate with Bubble light.";
    }
}
