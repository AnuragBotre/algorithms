package com.trendcore.sample.application;

public class Loggable {

    public static void log(Runnable runnable) {
        runnable.run();
    }

}
