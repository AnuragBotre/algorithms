package com.trendcore.factory;

public interface AbstractFactory<T> {

    T create(String choice);

}
