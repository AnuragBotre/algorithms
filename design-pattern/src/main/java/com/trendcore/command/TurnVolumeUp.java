package com.trendcore.command;

public class TurnVolumeUp implements Command{

    private ElectronicDevice context;

    public TurnVolumeUp(ElectronicDevice electronicDevice) {
        this.context = electronicDevice;
    }

    @Override
    public void execute() {
        context.volumeUp();
    }

    @Override
    public void undo() {
        context.volumeDown();
    }
}
