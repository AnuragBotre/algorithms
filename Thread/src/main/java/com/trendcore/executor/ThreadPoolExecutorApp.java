package com.trendcore.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Anurag
 */
public class ThreadPoolExecutorApp {

    public static void main(String[] args) {
        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();

        /*ScheduledExecutorService singleScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(10);*/
    }

}
