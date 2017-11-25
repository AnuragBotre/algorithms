package com.trendcore.map.iterator.failsafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Anurag
 */
public class FailSafeIteratorApp {

    public static void main(String[] args) {
        List<Object> copyOnWriteArrayList = new CopyOnWriteArrayList();

        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);
        copyOnWriteArrayList.add(4);

        Iterator<Object> iterator = copyOnWriteArrayList.iterator();

        for(;iterator.hasNext();){
            copyOnWriteArrayList.add(1);
            iterator.next();
        }



        //System.out.println(copyOnWriteArrayList);

    }

}
