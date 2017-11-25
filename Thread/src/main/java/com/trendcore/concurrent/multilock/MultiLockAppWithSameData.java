package com.trendcore.concurrent.multilock;

/**
 * Created by Anurag
 */
public class MultiLockAppWithSameData {

    public static void main(String[] args) {
        SharedDataStructure s = new SharedDataStructure();
        Thread thread1 = new Thread(new MultiLockThreadWithSameData(s));
        Thread thread2 = new Thread(new MultiLockThreadWithSameData(s));
        thread2.setName("Thread2");

        thread1.start();
        thread2.start();
    }

}
