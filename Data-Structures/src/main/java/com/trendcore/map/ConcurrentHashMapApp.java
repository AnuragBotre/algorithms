package com.trendcore.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Anurag
 */
public class ConcurrentHashMapApp {

    public static void main(String[] args) {
        Map concurrentHashMap = new ConcurrentHashMap();

        concurrentHashMap.put(1,"test");
        concurrentHashMap.put(2,"test1");


    }

}
