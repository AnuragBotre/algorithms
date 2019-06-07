package com.trendcore.factory.example2;

public class EmberToolkit implements AbstractFactory{

    @Override
    public CPU getCpu() {
        return new EmberCPU();
    }

    @Override
    public MMU getMmu() {
        return new EmberMMU();
    }
}
