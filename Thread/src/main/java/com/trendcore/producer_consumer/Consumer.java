package com.trendcore.producer_consumer;

import java.util.List;

public class Consumer implements Runnable {

    private final List list;

    private final int maxSize;


    public Consumer(List list, int maxSize) {
        this.list = list;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        boolean flag = true;

        while (flag) {

            //if list is empty then it should wait for producer to produce
            synchronized (list) {

                if(list.isEmpty()) {
                    /*
                        There is one problem in this approach
                        when the list is full then producer needs to wait
                        for the whole time for consumer to consume items
                        (to become list empty);
                     */
                    list.notifyAll();
                    waitForProducer();
                }

                Object remove = list.remove(0);
                System.out.println("Removed from the list :- " + remove);
                doSomeWork();
                list.notifyAll();
            }

        }


    }

    private void doSomeWork() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForProducer() {
        try {
            list.wait();
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
