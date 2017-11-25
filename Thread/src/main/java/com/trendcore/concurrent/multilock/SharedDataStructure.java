package com.trendcore.concurrent.multilock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anurag
 */
public class SharedDataStructure {

    private Integer counter;

    private Lock lock;

    public SharedDataStructure(){
        initializeData();
    }

    public Integer incrementCounter(){
        counter++;
        return counter;
    }

    public Integer decrementCounter(){
        counter--;
        return counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public Lock getLock(){
        return lock;
    }

    public void initializeData(){
        counter = new Integer(1);
        lock = new ReentrantLock();
    }

}
