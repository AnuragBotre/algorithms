package com.trendcore.synchronize.wait_notify;

import com.trendcore.synchronize.NotificationThread;

/**
 * Created by Anurag
 */
public class NotificationThreadApp {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NotificationThread("SharedString"));
        thread.start();

        Object obj  = new Object();

        obj.wait();
    }

}
