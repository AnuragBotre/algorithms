package com.trendcore.executor.fixthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Anurag
 */
public class FixedThreadPoolServiceApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);


        for(int i = 0 ; i < 20 ; i++){
            executorService.submit(new FixedThreadPoolTask("Task" + i));
        }

        executorService.shutdown();

    }
}
