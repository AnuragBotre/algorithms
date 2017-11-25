package com.trendcore.synchronize;

/**
 * Created by Anurag
 */
public class NotificationThread implements Runnable {

    private String sharedDataStructure;

    public NotificationThread(String data){
        sharedDataStructure = data;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {


            sharedDataStructure.notify();

        }
    }
}
