package com.trendcore.instrument;

import java.lang.instrument.Instrumentation;

public class PreMain {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        System.out.println("Initializing Agent.");
        instrumentation = inst;
    }

    public static long sizeof(Object o) {
        return instrumentation.getObjectSize(o);
    }

}
