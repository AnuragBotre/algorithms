package com.trendcore.executor.fixthreadpool;

/**
 * Created by Anurag
 */
public class FixedThreadPoolTask implements  Runnable {

    private String task;

    public FixedThreadPoolTask(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println("Task :- " + this.task + " Thread :- " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
