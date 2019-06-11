package com.trendcore.bridge;

public class TV implements Device {

    Switch button;

    public TV(Switch button) {
        this.button = button;
    }

    @Override
    public void on() {
        button.on();
    }

    @Override
    public void off() {
        button.off();
    }

    @Override
    public void performAction() {
        System.out.println("Show picture..");
    }
}
