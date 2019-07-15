package com.trendcore.observer;

public class Editor {

    private String file;

    private EventNotifier eventNotifier;

    public Editor() {

    }

    public void setEventNotifier(EventNotifier eventNotifier) {
        this.eventNotifier = eventNotifier;
    }

    public void openFile(String filePath) {
        this.file = "<Path-to-File>" +filePath;
        this.eventNotifier.notify("open", this.file);
    }

    public void saveFile() {
        if(this.file != null){
            this.eventNotifier.notify("save", this.file);
        }
    }
}
