package com.trendcore.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LoggableReadWriteReentrantLock implements ReadWriteLock {

    private LoggableReadLock readLock;

    private LoggableWriteLock writeLock;

    private ReentrantReadWriteLock reentrantReadWriteLock;

    private String label;

    private BlockingQueue<LogMessage> log;

    public void showLog() {

        for (LogMessage logMessage : log) {
            System.out.println(logMessage.label+","+logMessage.currentThread.getId()+","+logMessage.lockType.name()+","+logMessage.lockState.name()+","+logMessage.currentTimeMillis);
        }

    }

    public enum LockState{
        ACQUIRED,
        RELEASED;
    }

    public enum LockType{
        READ,
        WRITE;
    }

    public LoggableReadWriteReentrantLock(String label,BlockingQueue<LogMessage> log) {
        reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.log = log;
        initializeLocks(label);
        this.label = label;
    }

    private void initializeLocks(String label) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        this.readLock = new LoggableReadLock(readLock, label);
        this.writeLock = new LoggableWriteLock(writeLock, label);
    }

    public LoggableReadWriteReentrantLock(boolean fair) {
        reentrantReadWriteLock = new ReentrantReadWriteLock(fair);
    }

    class LoggableReadLock implements Lock {

        private String label;

        private ReentrantReadWriteLock.ReadLock readLock;

        public LoggableReadLock(ReentrantReadWriteLock.ReadLock readLock, String label) {
            this.readLock = readLock;
            this.label = label;
        }

        @Override
        public void lock() {
            readLock.lock();
            log.add(new LogMessage(LockState.ACQUIRED,LockType.READ,Thread.currentThread(),this.label,System.currentTimeMillis()));
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            readLock.lockInterruptibly();
        }

        @Override
        public boolean tryLock() {
            return readLock.tryLock();
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return readLock.tryLock(timeout, unit);
        }

        @Override
        public void unlock() {
            log.add(new LogMessage(LockState.RELEASED,LockType.READ,Thread.currentThread(),this.label,System.currentTimeMillis()));
            readLock.unlock();
        }

        @Override
        public Condition newCondition() {
            return readLock.newCondition();
        }

        @Override
        public String toString() {
            return readLock.toString();
        }
    }

    class LoggableWriteLock implements Lock {

        private ReentrantReadWriteLock.WriteLock writeLock;

        private String label;

        public LoggableWriteLock(ReentrantReadWriteLock.WriteLock writeLock, String label) {
            this.writeLock = writeLock;
            this.label = label;
        }

        @Override
        public void lock() {
            writeLock.lock();
            log.add(new LogMessage(LockState.ACQUIRED,LockType.WRITE,Thread.currentThread(),this.label,System.currentTimeMillis()));
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            writeLock.lockInterruptibly();
        }

        @Override
        public boolean tryLock() {
            return writeLock.tryLock();
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return writeLock.tryLock(timeout, unit);
        }

        @Override
        public void unlock() {
            log.add(new LogMessage(LockState.RELEASED,LockType.WRITE,Thread.currentThread(),this.label,System.currentTimeMillis()));
            writeLock.unlock();
        }

        @Override
        public Condition newCondition() {
            return writeLock.newCondition();
        }

        @Override
        public String toString() {
            return writeLock.toString();
        }
    }

    public static class LogMessage {

        public final long currentTimeMillis;
        public Thread currentThread;
        public LockState lockState;
        public LockType lockType;
        public String label;

        public LogMessage(LockState lockState, LockType lockType, Thread currentThread, String label, long currentTimeMillis) {
            this.lockState = lockState;
            this.lockType = lockType;
            this.currentThread = currentThread;
            this.currentTimeMillis = currentTimeMillis;
            this.label = label;
        }
    }


    public Lock readLock() {
        return this.readLock;
    }


    public Lock writeLock() {
        return this.writeLock;
    }
}
