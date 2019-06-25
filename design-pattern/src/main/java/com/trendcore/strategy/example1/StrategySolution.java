package com.trendcore.strategy.example1;

public abstract class StrategySolution implements Strategy {

    @Override
    public void solve() {
        start();
        while (nextTry() && !isSolution()) {

        }

        stop();
    }

    protected abstract void start();

    protected abstract boolean nextTry();

    protected abstract boolean isSolution();

    protected abstract void stop();
}
