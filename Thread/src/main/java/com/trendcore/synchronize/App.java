package com.trendcore.synchronize;

/**
 * Created by Anurag
 */
public class App {

    public int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public static void main(String[] args) {
        App app = new App();

        Thread incrementCounterThread = new Thread(new IncrementCounterThread(app));
        incrementCounterThread.start();

        Thread decrementCounterThread = new Thread(new DecrementCounterThread(app));
        decrementCounterThread.start();


    }

}
