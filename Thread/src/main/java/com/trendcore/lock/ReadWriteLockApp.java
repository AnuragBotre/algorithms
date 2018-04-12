package com.trendcore.lock;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockApp {

    interface Action{
        void execute();
    }

    public static void main(String[] args) {

        class CommonData{

            private Integer i;

            ReadWriteLock lock = new ReentrantReadWriteLock();

            public CommonData(int i) {
                this.i = i;
            }

            public Object getData() {
                try{
                    lock.readLock().lock();
                    System.out.println("Reading Data by Thread = " + Thread.currentThread().getName() + " i = " + i);
                    return i;
                }finally {
                    lock.readLock().unlock();
                }
            }



            public boolean tryWithLock(Action action){
                if(lock.readLock().tryLock()){
                    try{
                        System.out.println("Reading Data by Thread = " + Thread.currentThread().getName() + " i = " + i);
                    }finally {
                        lock.readLock().unlock();
                    }
                }else{
                    action.execute();
                }

                return true;
            }

            public boolean modifyData(int i) {
                try{
                    lock.writeLock().lock();
                    System.out.println("Modifying Data by Thread = " + Thread.currentThread().getName() + " i = "+i +
                            ". Enter No to resume thread");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextInt();
                    this.i = i;
                    return true;
                }finally {
                    lock.writeLock().unlock();
                }
            }
        }

        final CommonData c = new CommonData(10);

        Runnable readTasks = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //c.getData();
                    c.tryWithLock(new Action() {
                        @Override
                        public void execute() {
                            System.out.println("Can't acquire lock performing alternative action.");
                        }
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        Thread readThread1 = new Thread(readTasks);
        Thread readThread2 = new Thread(readTasks);
        Thread readThread3 = new Thread(readTasks);
        Thread readThread4 = new Thread(readTasks);

        Runnable writerTask = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    c.modifyData(new Random().nextInt());
                }
            }
        };

        Thread writerThread = new Thread(writerTask);

        System.out.println("Starting Application...");

        readThread1.start();
        readThread2.start();
        readThread3.start();
        readThread4.start();

        //writerThread.start();


        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 1:
                    c.modifyData(new Random().nextInt());
                    break;
                case 2:
                    flag = false;
                    break;
            }
        }

    }

}
