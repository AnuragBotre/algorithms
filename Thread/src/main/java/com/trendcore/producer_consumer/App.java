package com.trendcore.producer_consumer;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        int maxSize = 10;

        Thread producerThread = new Thread(new Producer(list,maxSize));
        Thread consumerThread = new Thread(new Consumer(list,maxSize));

        producerThread.start();
        consumerThread.start();
    }

}
