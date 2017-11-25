package com.trendcore.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anurag
 */
public class ShareObject {

    private int count;

    private Lock lock;
    private final Condition condition;

    public ShareObject() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Condition getCondition() {
        return condition;
    }
}
