package com.trendcore.lock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class SemaphoreApp {

    public static void main(String[] args) throws IOException {
        Semaphore semaphore = new Semaphore();

        List<Thread> threadList = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){
            Thread thread = new Thread(new SemaphoreThread(semaphore));
            threadList.add(thread);
            thread.start();
        }

        int choice = 0;
        while(choice != 4){
            Reader reader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            choice = Integer.parseInt(line);
            switch (choice){
                case 1:
                    System.out.println(semaphore.getCurrent());
                    break;
                case 2:
                    for(Thread thread : threadList){
                        System.out.println(thread.getName() + " Thread State :- " + thread.getState());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private static class SemaphoreThread implements Runnable {

        private Semaphore semaphore;

        public SemaphoreThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            boolean flag = true;
            while (flag){
                try {
                    semaphore.take();

                    System.out.println("Thread name :- " + Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    sleep(1000);
                    try {
                        semaphore.relase();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                sleep(4000);
            }
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
