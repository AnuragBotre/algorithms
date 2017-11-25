package com.trendcore.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anurag
 */
public class DecrementThread implements Runnable {

    private final SharedObject sharedObject;

    public DecrementThread(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {

        boolean flag = true;

        //ReentrantLock lock = new ReentrantLock();

        while (flag) {
            try {
                //lock.lock();
                sharedObject.getObjectLock().lock();
                sharedObject.setCounter(sharedObject.getCounter() - 1);

                System.out.println(" Decrement Thread :-  " + sharedObject.getCounter());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            } finally {
                //lock.unlock();
                sharedObject.getObjectLock().unlock();
            }
        }
    }
}
