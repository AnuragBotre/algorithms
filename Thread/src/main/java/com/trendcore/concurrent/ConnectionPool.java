package com.trendcore.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * This is a kind of producer consumer problem.
 *
 * Created by Anurag
 */
public class ConnectionPool {

    private List connections;

    private Lock lock;
    private Condition condition;

    private boolean awakeWaitingThread;

    public boolean isAwakeWaitingThread() {
        return awakeWaitingThread;
    }

    public void setAwakeWaitingThread(boolean awakeWaitingThread) {
        this.awakeWaitingThread = awakeWaitingThread;
    }

    public Object getConnection() {

        lock.lock();
        try {

            while (connections.isEmpty()) {

                try {
                    System.out.println("Thread " + Thread.currentThread().getName() + " waiting for connection.");


                    condition.await();

                    System.out.println("Thread " + Thread.currentThread().getName() + " awake from wait.");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Removing connection for Thread " + Thread.currentThread().getName());
            Object connection = connections.remove(0);
            System.out.println("Returning connection " + connection + " to thread " + Thread.currentThread().getName());

            return connection;
        } finally {
            lock.unlock();
        }
    }

    public void returnConnection(Object o) {


        lock.lock();

        try {
            System.out.println("Adding connection " + o + " from thread " + Thread.currentThread().getName());

            connections.add(o);

            if(awakeWaitingThread) {
                System.out.println("Sending signal to condition.");
                //condition.signal();
            }
            sendSignalToWaitingThread();
        } finally {
            lock.unlock();
        }


        setCurrentThreadToIdleState(100);
    }

    private void setCurrentThreadToIdleState(int millis) {
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {

        }
    }


    public void initialize(int noOfConnections) {

        lock = new ReentrantLock();
        condition = lock.newCondition();

        connections = new ArrayList(10);
        for (int i = 0; i < noOfConnections; i++) {
            connections.add(new String("Connection-" + i));
        }
    }

    public void sendSignalToWaitingThread() {
        lock.lock();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
