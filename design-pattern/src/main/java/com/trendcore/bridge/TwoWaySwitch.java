package com.trendcore.bridge;

public class TwoWaySwitch implements Switch {

    @Override
    public void on() {
        System.out.println("If the another pair is on then send power supply.");
    }

    @Override
    public void off() {
        System.out.println("If the another pair is off then cut power supply.");
    }
}
