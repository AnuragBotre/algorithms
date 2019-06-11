package com.trendcore.bridge;

public class SimpleSwitch implements Switch {

    @Override
    public void on() {
        System.out.println("Switch on power supply");
    }

    @Override
    public void off() {
        System.out.println("Switch off power supply");
    }
}
