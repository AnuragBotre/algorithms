package com.trendcore.bridge;

public class ClientForSwitch {

    public static void main(String[] args) {

        Switch simpleSwitch = new SimpleSwitch();
        tryWithSwitch(simpleSwitch);

        System.out.println("With 2 way switch");

        Switch twoWaySwitch = new SimpleSwitch();
        tryWithSwitch(twoWaySwitch);
    }

    private static void tryWithSwitch(Switch button) {

        Device fan = new Fan(button);
        Device tv = new TV(button);

        fan.on();
        fan.off();
        fan.performAction();

        tv.on();
        tv.off();
        tv.performAction();
    }

}
