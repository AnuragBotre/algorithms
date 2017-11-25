package com.trendcore.reentrant;

/**
 * Created by Anurag
 */
public class Application {

    public static void main(String[] args) {

        SharedObject sharedObject = new SharedObject();

        Thread incrementThread = new Thread(new IncrementThread(sharedObject));
        Thread decrementThread = new Thread(new DecrementThread(sharedObject));

        incrementThread.start();
        decrementThread.start();
    }

}
