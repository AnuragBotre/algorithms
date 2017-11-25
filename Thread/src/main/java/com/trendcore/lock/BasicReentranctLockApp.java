package com.trendcore.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anurag
 */
public class BasicReentranctLockApp {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        /*lock.tryLock();

        try{
            lock.tryLock();
            lock.tryLock();
            lock.tryLock();
            System.out.println("heres");
        }finally {
            lock.unlock();
        }*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
            }
        });

        lock.lock();
        System.out.println("heres");

        thread.start();
    }

}
