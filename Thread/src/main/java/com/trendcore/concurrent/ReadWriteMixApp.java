package com.trendcore.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Anurag
 */
public class ReadWriteMixApp {

    public static void main(String[] args) {
        ReadWriteData readWriteData = new ReadWriteData();

        List keys = generateKeys();

        readWriteData.initialize();

        Thread readerThread = new Thread(new ReaderThread(readWriteData,keys));
        readerThread.start();
        readerThread.setName("ReaderThread");

        for(int i = 0 ; i < 5 ; i++){
            Thread writerThread = new Thread(new ReaderWriterThread("Reader-WriterThread-"+i,readWriteData,getIndexesForWriter(4)));
            writerThread.setName("WriterThread-"+i);
            writerThread.start();
        }
    }

    private static List getIndexesForWriter(int i) {
        List list = new ArrayList<>();

        int i1 = new Random().nextInt(i)+1;
        for(int j = 0; j < i1; j++){
            list.add(1000+j);
        }

        return list;
    }

    private static List generateKeys() {
        List keys = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++ ){
            keys.add((1000+i));
        }

        return keys;
    }

}
