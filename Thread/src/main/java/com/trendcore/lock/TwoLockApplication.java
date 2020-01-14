package com.trendcore.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class TwoLockApplication {

    public static void main(String[] args) throws InterruptedException {

        /*final ReadWriteLock readWriteLock1 = new ReentrantReadWriteLock();
        final ReadWriteLock readWriteLock2 = new ReentrantReadWriteLock();*/

        final BlockingQueue<LoggableReadWriteReentrantLock.LogMessage> linkedBlockingQueue = new LinkedBlockingQueue();



        final LoggableReadWriteReentrantLock readWriteLock1 = new LoggableReadWriteReentrantLock("Lock for Task1",linkedBlockingQueue);
        final LoggableReadWriteReentrantLock readWriteLock2 = new LoggableReadWriteReentrantLock("Lock for Task2",linkedBlockingQueue);

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
                            System.out.println("Performing Task1.");
                            linkedBlockingQueue.add(new LoggableReadWriteReentrantLock.LogMessage(LoggableReadWriteReentrantLock.LockState.ACQUIRED, LoggableReadWriteReentrantLock.LockType.WRITE,Thread.currentThread(),"\tPerforming Task1",System.currentTimeMillis()));
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
                        readWriteLock2.writeLock().lock();
                        System.out.println("Performing Task2.");
                        linkedBlockingQueue.add(new LoggableReadWriteReentrantLock.LogMessage(LoggableReadWriteReentrantLock.LockState.ACQUIRED, LoggableReadWriteReentrantLock.LockType.WRITE,Thread.currentThread(),"\tPerforming Task2",System.currentTimeMillis()));
                    } finally {
                        readWriteLock2.writeLock().unlock();
                    }
                    i++;
                }
                countDownLatch.countDown();
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();


        countDownLatch.await();

        /*readWriteLock1.showLog();
        readWriteLock2.showLog();*/
        for (LoggableReadWriteReentrantLock.LogMessage logMessage : linkedBlockingQueue) {
            System.out.println(logMessage.label+","+logMessage.currentThread.getId()+","+logMessage.lockType.name()+","+logMessage.lockState.name()+","+logMessage.currentTimeMillis);
        }

    }

}
