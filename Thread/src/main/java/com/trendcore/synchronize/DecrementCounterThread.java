package com.trendcore.synchronize;

/**
 * Created by Anurag
 */
public class DecrementCounterThread implements Runnable{

    private App app;

    private boolean flag = true;

    public DecrementCounterThread(App app) {
        this.app = app;
    }

    public void stopThread(){
        flag = false;
    }

    @Override
    public void run() {

        while (flag){

            synchronized (app){

                int counter = app.getCounter() - 1;
                app.setCounter(counter);
                System.out.println("Inside decrement thread " + app.getCounter());
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
