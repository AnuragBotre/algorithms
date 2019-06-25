package com.trendcore.strategy.example1;

public class Client {

    public static void main(String[] args) {
        Strategy strategy[] = {new Foo(),new Bar()};

        for (Strategy s : strategy) {
            s.solve();
        }
    }
}
