package com.trendcore.executor.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anurag
 */
public class ScheduledThreadPoolApp {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        // Will start command1 after 5 seconds
        //scheduledExecutorService.schedule(new ScheduledCommand(), 5L, TimeUnit.SECONDS);


        // Will start command 2 after 2 seconds, 7 seconds, 12 seconds ...
        //scheduledExecutorService.scheduleAtFixedRate(new ScheduledWithFixedRateCommand(), 2,5,TimeUnit.SECONDS);

        // Will start command 3 after 10 seconds and if command3 takes 2 seconds to be
        // executed also after 17, 24, 31, 38 seconds...

        /*for(int i = 0 ; i < 10 ; i++){
            scheduledExecutorService.scheduleWithFixedDelay(new ScheduledCommandWithFixedDelay(), 10L, 5L, TimeUnit.SECONDS);
        }*/

        scheduledExecutorService.scheduleWithFixedDelay(new ScheduledCommandWithFixedDelay(), 1L, 5L, TimeUnit.SECONDS);



    }
}
