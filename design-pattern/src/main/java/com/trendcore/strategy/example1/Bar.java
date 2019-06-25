package com.trendcore.strategy.example1;

public class Bar extends StrategySearch {

    int i = 1;

    @Override
    protected void postProcess() {

    }

    @Override
    protected boolean search() {
        if(i == 3)
            return true;
        return false;
    }

    @Override
    protected void preProcess() {
        i++;
    }
}
