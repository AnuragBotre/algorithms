package com.trendcore;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                while (flag) {
                    System.out.println("Inside Runnable");
                }
            }
        });

        t.setDaemon(true);
        t.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                while (flag) {
                    System.out.println("Inside Runnable 2");
                }
            }
        });

        t1.setDaemon(true);
        t1.start();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Exiting setDeamon Thread Method");
    }
}
