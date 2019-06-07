package com.trendcore.factory.example2;

public interface AbstractFactory<T> {

    CPU getCpu();

    MMU getMmu();

}
