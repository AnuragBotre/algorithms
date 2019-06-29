package com.trendcore.command;

public class TVTurnOnCommand implements Command{

    private ElectronicDevice context;

    public TVTurnOnCommand(ElectronicDevice context) {
        this.context = context;
    }

    @Override
    public void execute() {
        this.context.on();
    }

    @Override
    public void undo() {
        context.off();
    }
}
