package com.trendcore.memento;

public class Memento {

    private Editor editor;

    private String backup;

    public Memento(Editor editor) {
        this.editor = editor;
        this.backup = editor.backup();
    }

    public void restore() {
        editor.restore(backup);
    }
}
