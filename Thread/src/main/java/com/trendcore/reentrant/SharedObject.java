package com.trendcore.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anurag
 */
public class SharedObject {

    private int counter;

    private ReentrantLock objectLock;

    public SharedObject(){
        objectLock = new ReentrantLock();
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ReentrantLock getObjectLock() {
        return objectLock;
    }

    public void setObjectLock(ReentrantLock objectLock) {
        this.objectLock = objectLock;
    }
}
