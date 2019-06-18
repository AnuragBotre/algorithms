package com.trendcore.state.refactoredCode;

public class LockedState implements PlayerState {

    private AudioPlayer player;

    public LockedState(AudioPlayer context) {
        this.player = context;
    }

    @Override
    public void clickLock() {
        player.stopPlaying();
    }

    @Override
    public void clickPlay() {
        player.setState(new ReadyState(player));
        player.startPlaying();
    }

    @Override
    public void clickNext() {
        player.setNextSong();
    }

    @Override
    public void clickPrevious() {
        player.setPreviousSong();
    }
}
