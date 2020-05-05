package com.trendcore.sample.application;

public class LogAndExecute {

    public void method1() {
        Loggable.log(() -> {
            System.out.println("Need to know what happens with this.");
        });
    }

}
