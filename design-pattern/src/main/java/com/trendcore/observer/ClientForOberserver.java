package com.trendcore.observer;

public class ClientForOberserver {

    public static void main(String[] args) {
        Subscriber emailSubscriber = new EmailSubscriber("abc@pqe.com");
        Subscriber logSubscriber = new LogSubscriber();

        Editor editor = new Editor();
        EventNotifier eventNotifier = new EventNotifier();
        eventNotifier.addSubscriber(emailSubscriber);
        eventNotifier.addSubscriber(logSubscriber);


        editor.setEventNotifier(eventNotifier);
        editor.openFile("some.txt");
        editor.saveFile();


        System.out.println("Removing Email Subscriber.");
        eventNotifier.removeSubscriber(emailSubscriber);

        editor.openFile("some.txt");
        editor.saveFile();
    }

}
