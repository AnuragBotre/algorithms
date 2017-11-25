package com.trendcore.synchronize.wait_notify;

/**
 * Created by Anurag
 */
public class DecrementThread implements Runnable {

    private App app;

    private boolean flag = true;

    public DecrementThread(App app) {
        this.app = app;
    }

    public void stopThread(){
        flag = false;
    }

    @Override
    public void run() {

        while (flag){

            synchronized (app) {

                try {
                    int counter = app.getCounter() - 1;
                    app.setCounter(counter);

                    System.out.println("In decrement thread - " + app.getCounter());

                    System.out.println("Decrement Thread Calling wait method.");
                    app.wait();
                    app.notify();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
