package com.trendcore.synchronize.wait_notify;

/**
 * Created by Anurag
 */
public class IncrementThread implements Runnable {

    private App app;

    private boolean flag = true;

    public IncrementThread(App app) {
        this.app = app;
    }

    public void stopThread() {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {

            synchronized (app) {

                try {
                    int counter = app.getCounter() + 1;
                    app.setCounter(counter);

                    System.out.println("In increment thread - " + app.getCounter());

                    app.notify();
                    System.out.println("Increment Thread Calling wait method.");
                    app.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
