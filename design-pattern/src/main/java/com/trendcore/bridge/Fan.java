package com.trendcore.bridge;

public class Fan implements Device{

    private Switch button;

    public Fan(Switch button) {
        this.button = button;
    }

    @Override
    public void on() {
        this.button.on();
    }

    @Override
    public void off() {
        this.button.off();
    }

    @Override
    public void performAction() {
        System.out.println("Start rotating..");
    }
}
