package com.trendcore.synchronize;

/**
 * Created by Anurag
 */
public class IncrementCounterThread implements Runnable {

    private App app;

    private boolean flag = true;

    public IncrementCounterThread(App app) {
        this.app = app;
    }

    public void stopThread() {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {

            synchronized (app) {

                int counter = app.getCounter() + 1;
                app.setCounter(counter);
                System.out.println("Inside increment thread " + app.getCounter());
            }


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
