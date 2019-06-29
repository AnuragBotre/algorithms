package com.trendcore.command;

public class TVTurnOffCommand implements Command{

    private ElectronicDevice context;

    public TVTurnOffCommand(ElectronicDevice context) {
        this.context = context;
    }

    @Override
    public void execute() {
        this.context.off();
    }

    @Override
    public void undo() {
        this.context.on();
    }
}
