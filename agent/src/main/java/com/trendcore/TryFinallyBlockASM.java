package com.trendcore;

public class TryFinallyBlockASM {

    public void method1(int i, String j) {

        Profiler.pushMethod("TryFinallyBlockASM","profileMe", System.currentTimeMillis(), "java.lang.String,java.lang.String");

        try {

            /*TODO : Write in try catch*/
            System.out.println("method 1");
            /*TODO : Write in try catch - End */

        } finally {
            Profiler.popMethod(System.currentTimeMillis());
        }

    }

}
