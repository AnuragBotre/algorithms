package com.trendcore.state.refactoredCode;

public class PlayingState implements PlayerState {

    private AudioPlayer player;

    public PlayingState(AudioPlayer audioPlayer) {
        this.player = audioPlayer;
    }

    @Override
    public void clickLock() {
        player.stopPlaying();
        player.setState(new LockedState(player));
    }

    @Override
    public void clickPlay() {
        player.startPlaying();
        player.setState(new ReadyState(player));
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
