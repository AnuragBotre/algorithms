package com.trendcore.map.iterator.failfast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Anurag
 */
public class HashMapApp {

    public static void main(String[] args) {
        //concurrentModificationException();

        iterationWithoutConcurrentModificationExceptionUsingIteratorRemoveMethod();

        //iteratingMapWithoutIteratorWillThrowConcurrentModificationExcepn();
    }

    private static void iteratingMapWithoutIteratorWillThrowConcurrentModificationExcepn() {
        Map<Object,Object> map = new HashMap();

        map.put(1,"test1");
        map.put(2,"test2");
        map.put(3,"test3");

        Iterator<Object> iterator = map.keySet().iterator();

        while(iterator.hasNext()){
            Object next = iterator.next();
            if(next.equals(1)){
                map.remove(next);
            }
        }
    }

    private static void iterationWithoutConcurrentModificationExceptionUsingIteratorRemoveMethod() {
        Map<Object,Object> map = new HashMap();

        map.put(1,"test1");
        map.put(2,"test2");
        map.put(3,"test3");

        Iterator<Map.Entry<Object, Object>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Object, Object> entry = iterator.next();

            if(entry.getKey().equals(1)){
                iterator.remove();
            }
        }

        System.out.println(map);
    }

    private static void concurrentModificationException() {
        Map<Object,Object> map = new HashMap();

        map.put(1,"test1");
        map.put(2,"test2");
        map.put(3,"test3");

        for(Map.Entry entry : map.entrySet()){
            /*map.put(4,"test4");
            map.remove(1);*/
            entry.setValue("test2");
        }

        System.out.println(map);
    }

}
