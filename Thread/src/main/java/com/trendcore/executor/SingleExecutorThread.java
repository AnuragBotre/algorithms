package com.trendcore.executor;

/**
 * Created by Anurag
 */
public class SingleExecutorThread implements Runnable {

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            sleep();
            System.out.println("Thread  "+ Thread.currentThread().getName() + " executing task. "+ i);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
