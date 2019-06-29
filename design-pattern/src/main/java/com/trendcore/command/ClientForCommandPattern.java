package com.trendcore.command;

public class ClientForCommandPattern {

    public static void main(String[] args) {
        ElectronicDevice electronicDevice = TVRemote.getDevice();
        Command command = new TVTurnOnCommand(electronicDevice);
        DeviceButton onButton = new DeviceButton(command);
        onButton.press();

        DeviceButton offButton = new DeviceButton(new TVTurnOffCommand(electronicDevice));
        offButton.press();

        DeviceButton volumeUpButton = new DeviceButton(new TurnVolumeUp(electronicDevice));
        volumeUpButton.press();

        DeviceButton volumeDownButton = new DeviceButton(new TurnVolumeDown(electronicDevice));
        volumeDownButton.press();

        volumeDownButton.undo();

    }

}
