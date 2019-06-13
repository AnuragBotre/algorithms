package com.trendcore.decorator.example2;

public abstract class ChristmasTreeDecorator implements ChristmasTree{

    private ChristmasTree christmasTree;

    public ChristmasTreeDecorator(ChristmasTree christmasTree) {
        this.christmasTree = christmasTree;
    }

    @Override
    public String getDescription() {
        return christmasTree.getDescription();
    }
}
