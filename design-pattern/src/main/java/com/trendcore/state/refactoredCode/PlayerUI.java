package com.trendcore.state.refactoredCode;

public class PlayerUI {

    PlayerState state;

    public PlayerUI(PlayerState playable) {
        this.state = playable;
    }

    public void play() {
        state.clickPlay();
    }

    public void pause() {
        state.clickLock();
    }

    public void next() {
        state.clickNext();
    }

    public void previous() {
        state.clickPrevious();
    }
}
