package com.trendcore.concurrent.multilock;

import java.util.Random;

/**
 * Created by Anurag
 */
public class MultiLockThread implements Runnable {

    private SharedDataStructure dataStructure1;

    private SharedDataStructure dataStructure2;

    private SharedDataStructure dataStructure3;

    public MultiLockThread(SharedDataStructure dataStructure1, SharedDataStructure dataStructure2, SharedDataStructure dataStructure3) {
        this.dataStructure1 = dataStructure1;
        this.dataStructure2 = dataStructure2;
        this.dataStructure3 = dataStructure3;
    }

    @Override
    public void run() {

        boolean flag = true;

        while (flag) {

            if (new Random().nextInt(100) % 2 == 0) {
                scheme1();
            } else {
                scheme2();
            }

            //sleep();
        }

    }

    private void sleep() {

        try {
            Thread.currentThread().sleep(new Random().nextInt(100)+100);
        } catch (InterruptedException e) {

        }

    }

    private void scheme2() {
        dataStructure2.getLock().lock();
        dataStructure3.getLock().lock();
        dataStructure1.getLock().lock();

        try {

            System.out.println("Scheme-2 Thread "+Thread.currentThread().getName()+" Incrementing Datastructure3 counter :- " + dataStructure3.incrementCounter());
            System.out.println("Scheme-2 Thread "+Thread.currentThread().getName()+" Decrementing Datastructure2 counter :- " + dataStructure2.decrementCounter());
            System.out.println("Scheme-2 Thread "+Thread.currentThread().getName()+" Incrementing Datastructure1 counter :- " + dataStructure1.incrementCounter());

        } finally {
            dataStructure2.getLock().unlock();
            dataStructure3.getLock().unlock();
            dataStructure1.getLock().unlock();
        }
    }

    private void scheme1() {
        dataStructure1.getLock().lock();
        dataStructure2.getLock().lock();
        dataStructure3.getLock().lock();

        try {

            System.out.println("Scheme-1 Thread "+Thread.currentThread().getName()+" Incrementing Datastructure1 counter :- " + dataStructure1.incrementCounter());
            System.out.println("Scheme-1 Thread "+Thread.currentThread().getName()+" Decrementing Datastructure2 counter :- " + dataStructure2.decrementCounter());
            System.out.println("Scheme-1 Thread "+Thread.currentThread().getName()+" Incrementing Datastructure3 counter :- " + dataStructure3.incrementCounter());

        } finally {
            dataStructure3.getLock().unlock();
            dataStructure2.getLock().unlock();
            dataStructure1.getLock().unlock();
        }
    }
}
