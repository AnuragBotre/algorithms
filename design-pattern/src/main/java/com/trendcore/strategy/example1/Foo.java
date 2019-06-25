package com.trendcore.strategy.example1;

public class Foo extends StrategySolution {

    int i;

    @Override
    protected void start() {
        i = 1;
    }

    @Override
    protected boolean nextTry() {
        i++;
        return true;
    }

    @Override
    protected boolean isSolution() {
        if(i==3)
            return true;
        return false;
    }

    @Override
    protected void stop() {
        System.out.println("Stopped.");
    }
}
