package com.trendcore.executor.scheduled;

/**
 * Created by Anurag
 */
public class ScheduledCommandWithFixedDelay implements Runnable {

    private long now;

    @Override
    public void run() {
        long prev = now;
        now = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() +" Inside scheduled Command with Fixed Delay. Delay :- " + (now - prev));
        sleep(10000);
        System.out.println(Thread.currentThread().getName() +" Task Finished " + (now - prev));
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
