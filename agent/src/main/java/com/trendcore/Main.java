package com.trendcore;

import com.trendcore.instrument.PreMain;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map map = new HashMap<>();

        map.put("1","ABC");

        System.out.println(PreMain.sizeof(map));
    }

}
