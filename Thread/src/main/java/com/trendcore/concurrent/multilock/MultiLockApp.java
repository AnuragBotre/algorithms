package com.trendcore.concurrent.multilock;

import java.lang.reflect.Method;

/**
 * Created by Anurag
 */
public class MultiLockApp {

    public static void main(String[] args) {
        SharedDataStructure dataStructure1 = new SharedDataStructure();
        SharedDataStructure dataStructure2 = new SharedDataStructure();
        SharedDataStructure dataStructure3 = new SharedDataStructure();

        Thread thread1 = new Thread(new MultiLockThread(dataStructure1,dataStructure2,dataStructure3));
        Thread thread2 = new Thread(new MultiLockThread(dataStructure1,dataStructure2,dataStructure3));

        thread1.start();
        thread2.start();
    }

}
