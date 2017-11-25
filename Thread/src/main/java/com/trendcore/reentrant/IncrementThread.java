package com.trendcore.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anurag
 */
public class IncrementThread implements Runnable {

    private SharedObject sharedObject;

    public IncrementThread(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {
        boolean flag = true;

        //ReentrantLock lock = new ReentrantLock();

        while (flag) {
            try {

                //Lock needs to be common across the thread

                //lock.lock();
                sharedObject.getObjectLock().lock();
                sharedObject.setCounter(sharedObject.getCounter() + 1);

                System.out.println(" Increment Thread :-  " + sharedObject.getCounter());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }finally {
                //lock.unlock();
                sharedObject.getObjectLock().unlock();
            }
        }
    }
}
