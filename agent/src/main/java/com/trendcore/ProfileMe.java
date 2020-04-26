package com.trendcore;

import java.io.PrintStream;

public class ProfileMe {

    public void profileMe(String a,String b){
        Profiler.pushMethod("profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String");

        /* do call */
        //method1();
        for(int i = 0; i < 10000; ++i) {
            System.out.println("something");
        }

        Profiler.popMethod(System.currentTimeMillis());
    }

    private void method1() {

    }

}
