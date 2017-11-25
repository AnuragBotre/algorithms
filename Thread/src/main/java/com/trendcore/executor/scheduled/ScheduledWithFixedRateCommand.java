package com.trendcore.executor.scheduled;

/**
 * Created by Anurag
 */
public class ScheduledWithFixedRateCommand implements Runnable {

    private long now;

    private int createExceptionAt5;

    @Override
    public void run() {
        long prev = now;
        now = System.currentTimeMillis();
        System.out.println("Inside command with Fixed Rate. Delay :- " + (now - prev));

        createExceptionAt5++;

        if(createExceptionAt5 == 5)
            throw new RuntimeException("Exception.");

        sleep(100);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
