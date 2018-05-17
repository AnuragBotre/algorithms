package com.trendcore.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TwoLockApplication {

    public static void main(String[] args) {

        final ReadWriteLock readWriteLock1 = new ReentrantReadWriteLock();
        final ReadWriteLock readWriteLock2 = new ReentrantReadWriteLock();


        Runnable task1 = new Runnable() {
            @Override
            public void run() {

                while(true){
                    try{
                        readWriteLock1.writeLock().lock();
                        try{
                            readWriteLock2.writeLock().lock();
                            System.out.println("Performing Task1.");
                        }finally {
                            readWriteLock2.writeLock().unlock();
                        }
                    }finally {
                        readWriteLock1.writeLock().unlock();
                    }
                }
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        readWriteLock2.writeLock().lock();
                        System.out.println("Performing Task2.");
                    } finally {
                        readWriteLock2.writeLock().unlock();
                    }
                }
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

    }

}
