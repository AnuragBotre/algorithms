package com.trendcore.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadLockWithLogger extends ReentrantReadWriteLock.ReadLock {

    public ReadLockWithLogger(ReentrantReadWriteLock lock) {
        super(lock);
    }



}
