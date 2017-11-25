package com.trendcore.join;

/**
 * Created by Anurag
 */
public class App {

    public static void main(String[] args) {
        Thread simpleThread1 = new Thread(new SimpleThread());
        Thread simpleThread2 = new Thread(new SimpleThread());
        Thread simpleThread3 = new Thread(new SimpleThread());

        simpleThread1.start();
        simpleThread2.start();

        try {
            simpleThread2.join();
        } catch (InterruptedException e) {

        }

        simpleThread3.start();
    }

}
