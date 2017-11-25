package com.trendcore.executor.scheduled;

/**
 * Created by Anurag
 */
public class ScheduledCommand implements Runnable {

    @Override
    public void run() {

        System.out.println("Running Task inside Schedulled Command.");

    }

}
