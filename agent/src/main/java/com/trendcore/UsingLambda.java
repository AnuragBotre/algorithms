package com.trendcore;

public class UsingLambda {

    public void profileMe(String a, String b) {
        Profiler.wrap(() -> {
            System.out.println(a + " " + b);
            return 1;
        });
    }

}
