package com.trendcore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Also known as Functional Interfaces.
 * Created by Anurag
 */
public class SingleAbstractInterfaces {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        /**
         * Imperative Coding Style
         */
        /*for (int i = 0 ; i < 10 ; i++) {
            int index = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Runnable Task " + index);
                }
            });
        }

        System.out.println("Task Started....");*/

        IntStream
                .range(0, 10)
                .forEach(value -> {
                    executorService.submit(() -> System.out.println("Runnable Task " + value));
                });

        System.out.println("Task Started....");

        executorService.shutdown();
    }

}
