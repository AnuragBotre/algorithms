package com.trendcore.command;

public class TurnVolumeDown implements Command{

    private ElectronicDevice context;

    public TurnVolumeDown(ElectronicDevice context) {
        this.context = context;
    }

    @Override
    public void execute() {
        context.volumeDown();
    }

    @Override
    public void undo() {
        context.volumeUp();
    }
}
