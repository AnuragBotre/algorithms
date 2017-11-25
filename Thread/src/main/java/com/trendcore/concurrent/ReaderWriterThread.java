package com.trendcore.concurrent;

import java.util.List;
import java.util.Random;

/**
 * Created by Anurag
 */
public class ReaderWriterThread implements Runnable{

    private final ReadWriteData readWriteData;

    private List indexes;

    private String writerThreadValue;

    private boolean flag;


    public ReaderWriterThread(String s, ReadWriteData readWriteData, List indexesForWriter) {
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

                readWriteData.read(o);

                sleep(100 + new Random().nextInt(100));

                readWriteData.write(o, writerThreadValue);
            } catch (Exception e) {

            }

            sleep(100 + new Random().nextInt(100));
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
