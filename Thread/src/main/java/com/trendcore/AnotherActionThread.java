package com.trendcore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class AnotherActionThread implements Runnable {

    private List list;

    public AnotherActionThread(ArrayList list) {
        this.list = list;
    }

    @Override
    public void run() {

        for (int i = 0 ; i < 10 ; i++){
            list.add(i+5);
        }

        Runtime rs =  Runtime.getRuntime();
        rs.freeMemory();

    }
}
