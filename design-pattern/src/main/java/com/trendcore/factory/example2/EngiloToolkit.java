package com.trendcore.factory.example2;

public class EngiloToolkit implements AbstractFactory{

    @Override
    public CPU getCpu() {
        return new EngiloCPU();
    }

    @Override
    public MMU getMmu() {
        return new EngileMMU();
    }
}
