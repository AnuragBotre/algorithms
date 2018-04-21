package com.trendcore;

import java.util.Set;

public class ListOfThreads {

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                while(flag){
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);

        t1.start();
        t2.start();
        t3.start();

        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        for(Thread t : threads){
            System.out.println(t.getStackTrace()[0].getClassName() + " " + t.getStackTrace()[0].getMethodName());
        }

    }

}
