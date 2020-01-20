package com.trendcore.lock;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class TwoLockApplication {

    static class LoggableLockEventProcessor implements LoggableLockEvents{

        @Override
        public void lockAcquired(Thread acquiredByThread, String resourceIdentifier) {
            System.out.println("Thread#"+acquiredByThread.getId()+" " +resourceIdentifier + " - " + " lock acquired.");
        }

        @Override
        public void releasedLock(Thread acquiredByThread, String resourceIdentifier) {
            System.out.println("Thread#"+acquiredByThread.getId()+" " +resourceIdentifier+ " - " + " lock released.");
        }

        @Override
        public void threadParked(Thread parkedThread, String resourceIdentifier) {
            System.out.println("Thread#"+parkedThread.getId()+" " +resourceIdentifier + " waiting for lock.");
        }

        @Override
        public void releasedSharedLock(Thread acquiredByThread, String resourceIdentifier) {
            System.out.println("Thread#"+acquiredByThread.getId()+" " +resourceIdentifier+ " - " + " shared lock released.");
        }

        @Override
        public void lockAcquiredShared(Thread acquiredByThread, String resourceIdentifier) {
            System.out.println("Thread#"+acquiredByThread.getId()+" " +resourceIdentifier+ " - " + " shared lock acquired.");
        }
    }


    public static void main(String[] args) throws InterruptedException {

        LoggableLockEvents loggableLockEventProcessor = new LoggableLockEventProcessor();


        final LoggableReentrantReadWriteLock readWriteLock1 = new LoggableReentrantReadWriteLock("Task1#"+ UUID.randomUUID(),true,loggableLockEventProcessor);
        final LoggableReentrantReadWriteLock readWriteLock2 = new LoggableReentrantReadWriteLock("Task2#"+ UUID.randomUUID(),true,loggableLockEventProcessor);

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i < 1000){
                    try{
                        readWriteLock1.writeLock().lock();
                        try{
                            readWriteLock2.writeLock().lock();
                            System.out.println("\t\tPerforming Task1.");
                            //linkedBlockingQueue.add(new LoggableReadWriteReentrantLock.LogMessage(LoggableReadWriteReentrantLock.LockState.ACQUIRED, LoggableReadWriteReentrantLock.LockType.WRITE,Thread.currentThread(),"\tPerforming Task1",System.currentTimeMillis()));
                        }finally {
                            readWriteLock2.writeLock().unlock();
                        }
                    }finally {
                        readWriteLock1.writeLock().unlock();
                    }
                    i++;
                }
                countDownLatch.countDown();
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i < 1000) {
                    try {
                        readWriteLock2.readLock().lock();
                        System.out.println("\t\tPerforming Task2.");
                        //linkedBlockingQueue.add(new LoggableReadWriteReentrantLock.LogMessage(LoggableReadWriteReentrantLock.LockState.ACQUIRED, LoggableReadWriteReentrantLock.LockType.WRITE,Thread.currentThread(),"\tPerforming Task2",System.currentTimeMillis()));
                    } finally {
                        readWriteLock2.readLock().unlock();
                    }
                    i++;
                }
                countDownLatch.countDown();
            }
        };

        task2.run();

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        countDownLatch.await();

        /*readWriteLock1.showLog();
        readWriteLock2.showLog();*/
        /*for (LoggableReadWriteReentrantLock.LogMessage logMessage : linkedBlockingQueue) {
            System.out.println(logMessage.label+","+logMessage.currentThread.getId()+","+logMessage.lockType.name()+","+logMessage.lockState.name()+","+logMessage.currentTimeMillis);
        }*/

    }


}
