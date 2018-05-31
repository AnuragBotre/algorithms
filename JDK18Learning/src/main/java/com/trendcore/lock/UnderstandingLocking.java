package com.trendcore.lock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class UnderstandingLocking implements Lock{

    private AtomicInteger lock = new AtomicInteger(0);

    private List<Thread> queue;

    public UnderstandingLocking(){
        queue = new LinkedList();
    }

    @Override
    public void lock() {
        if(lock.compareAndSet(0, 1)){

        }else{
            Thread currentThread = Thread.currentThread();
            queue.add(currentThread);
            LockSupport.park(currentThread);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if(queue != null && !queue.isEmpty()){
            Thread waitingThread = queue.remove(0);
            lock.set(0);
            LockSupport.unpark(waitingThread);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        class CommonData{
            UnderstandingLocking lock = new UnderstandingLocking();

            private List list = new ArrayList();

            public void add(Object a){
                try{
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+ " adding data.");
                    list.add(a);
                    /*Scanner scanner = new Scanner(System.in);
                    scanner.next();*/
                }finally {
                    lock.unlock();
                }
            }

            public void remove(){
                try{
                    lock.lock();
                    if(list.size() > 0) {
                        list.remove(0);
                    }
                }finally {
                    lock.unlock();
                }
            }
        }

        final CommonData commonData = new CommonData();

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                while(flag) {

                    try {
                        //System.out.println(Thread.currentThread().getName()+ " adding data.");
                        commonData.add(11);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        Thread thread = new Thread(task1);
        thread.start();

        Thread thread1 = new Thread(task1);
        thread1.start();
    }
}
