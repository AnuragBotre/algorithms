package com.trendcore.observer;

public class EmailSubscriber implements Subscriber {

    private String emailAddress;

    public EmailSubscriber(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void notify(String eventType, String args) {
        System.out.println("Sending Email to " + emailAddress + " for "+ eventType + " " + args);
    }
}
