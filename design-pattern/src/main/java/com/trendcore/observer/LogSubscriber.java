package com.trendcore.observer;

public class LogSubscriber implements Subscriber {

    public LogSubscriber() {
    }

    @Override
    public void notify(String eventType, String args) {
        System.out.println("Received Event " + eventType + " args " + args);
    }
}
