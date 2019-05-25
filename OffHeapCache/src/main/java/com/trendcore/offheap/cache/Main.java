package com.trendcore.offheap.cache;

import com.graphaware.offheap.map.OffHeapMap;
import com.graphaware.offheap.map.Value;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        OffHeapMap o = new OffHeapMap(null, 1, 0);
        OffHeapMapAdapter<Object> offHeapMap = new OffHeapMapAdapter(o);

        offHeapMap.put("abc","test");
        offHeapMap.put("pqe",new User(1));
        offHeapMap.put("xyz",new User(2));

        Object abc = offHeapMap.get("abc");
        System.out.println(((Value)abc).get());

        offHeapMap.entrySet().stream().forEach(o1 -> System.out.println(((Value)((Map.Entry)o1).getValue()).get()));
    }

}
