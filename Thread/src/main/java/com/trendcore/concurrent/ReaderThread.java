package com.trendcore.concurrent;

import java.util.List;
import java.util.Random;

/**
 * Created by Anurag
 */
public class ReaderThread implements Runnable {

    private final List keys;

    private ReadWriteData readWriteData;

    private boolean flag;

    public ReaderThread(ReadWriteData readWriteData, List keys) {
        this.readWriteData = readWriteData;
        this.keys = keys;
    }

    public void stopThread(){
        flag = false;
    }

    @Override
    public void run() {

        flag = true;

        while (flag) {
            try {
                readWriteData.read(keys.get(new Random().nextInt(5)));
            } catch (Exception e) {

            }

            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100+new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
