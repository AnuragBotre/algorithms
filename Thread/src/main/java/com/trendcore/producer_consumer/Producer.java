package com.trendcore.producer_consumer;

import java.util.List;

public class Producer implements Runnable {

    private final int maxSize;
    private List list;

    public Producer(List list, int maxSize) {
        this.list = list;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        boolean flag = true;

        int counter = 0;

        while (flag) {
            synchronized (list) {
                //if list is full then it should wait for consumer to consume
                if(list.size() >= maxSize) {
                    list.notifyAll();
                    waitForConsumer();
                }

                System.out.println("Produced item :- " + counter);
                list.add("counter-"+counter);
                doSomeWork();
                counter++;

                /*
                    This notify statement is added because
                    consumer thread should not wait for to completely fill up
                    list
                    consumer can start picking up elements as soon as they are produced.
                 */
                list.notifyAll();
            }
        }
    }

    private void doSomeWork() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForConsumer() {
        try {

            list.wait();
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
