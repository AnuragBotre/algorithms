package com.trendcore;

public class TryFinallyBlockASM {

    public void method1(int i,String j) {

        Profiler.pushMethod("profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String");

        try {

            System.out.println("method 1");

        } finally {
            Profiler.popMethod(System.currentTimeMillis());
        }

    }

}
