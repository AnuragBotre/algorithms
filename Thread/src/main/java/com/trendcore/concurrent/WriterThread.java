package com.trendcore.concurrent;

import java.util.List;
import java.util.Random;

/**
 * Created by Anurag
 */
public class WriterThread implements Runnable{

    private final ReadWriteData readWriteData;

    private List indexes;

    private String writerThreadValue;

    private boolean flag;

    public WriterThread(String writerThreadValue, ReadWriteData readWriteData, List indexes) {
        this.readWriteData = readWriteData;
        this.indexes = indexes;
        this.writerThreadValue = writerThreadValue;
    }

    public void stopThread(){
        flag = false;
    }

    @Override
    public void run() {

        flag = true;

        while (flag) {
            try {
                Object o = indexes.get(new Random().nextInt(indexes.size()));
                readWriteData.write(o, writerThreadValue);
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
