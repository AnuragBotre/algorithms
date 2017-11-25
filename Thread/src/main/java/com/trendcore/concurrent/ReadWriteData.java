package com.trendcore.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anurag
 */
public class ReadWriteData {

    private Map map;

    private Lock readLock;


    public Object read(Object key) {
        readLock.lock();
        try {
            Object o = map.get(key);
            System.out.println("Thread " + Thread.currentThread().getName() + " started reading data " + o + " from location " + key);
            doTask();
            o = map.get(key);
            System.out.println("Thread " + Thread.currentThread().getName() + " read data " + o + " from location " + key);
            return o;
        } finally {
            readLock.unlock();
        }

        /*synchronized (map){
            Object o = map.get(key);
            System.out.println("Thread " + Thread.currentThread().getName() + " started reading data " + o + " from location " + key);
            doTask();
            o = map.get(key);
            System.out.println("Thread " + Thread.currentThread().getName() + " read data " + o + " from location " + key);
            return o;
        }*/

    }

    private void doTask() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(Object key, Object value) {
        readLock.lock();
        try{
            //System.out.println("Thread " + Thread.currentThread().getName() + " writing data " + value + " at location " + key);
            map.put(key, value);
        }finally {
            readLock.unlock();
        }

        //map.put(key, value);
    }


    public void initialize() {

        readLock = new ReentrantLock();
        map = new HashMap();
    }
}
