package com.trendcore.state.refactoredCode;

public class ReadyState implements PlayerState {

    private AudioPlayer player;

    public ReadyState(AudioPlayer audioPlayer) {
        player = audioPlayer;
    }

    @Override
    public void clickLock() {
        player.stopPlaying();
        player.setState(new LockedState(player));
    }

    @Override
    public void clickPlay() {
        player.setState(new PlayingState(player));
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
