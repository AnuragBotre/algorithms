package com.trendcore.command;

public class TVDevice implements ElectronicDevice {

    private int volume;

    @Override
    public void on() {
        System.out.println("TV turn On.");
    }

    @Override
    public void off() {
        System.out.println("TV turn Off.");
    }

    @Override
    public void volumeUp() {
        volume++;
        System.out.println("Volume At : - " + volume);
    }

    @Override
    public void volumeDown() {
        volume--;
        System.out.println("Volume At : - " + volume);
    }
}
