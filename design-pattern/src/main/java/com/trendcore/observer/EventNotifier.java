package com.trendcore.observer;

import java.util.ArrayList;
import java.util.List;

public class EventNotifier {

    private List<Subscriber> subscribers;

    public EventNotifier() {
        subscribers = new ArrayList<>();
    }

    public void notify(String eventType, String args) {
        subscribers.forEach(s -> s.notify(eventType,args));
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
}
