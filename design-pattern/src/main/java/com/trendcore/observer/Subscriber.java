package com.trendcore.observer;

public interface Subscriber {

    void notify(String eventType, String args);
}
