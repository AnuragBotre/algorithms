package com.trendcore.state.refactoredCode;

public class AudioPlayer {

    PlayerState playable;

    //Refer this
    //https://refactoring.guru/design-patterns/state
    private PlayerUI ui;

    public AudioPlayer() {
        this.playable = new ReadyState(this);
        ui = new PlayerUI(playable);
    }

    public void setState(PlayerState playable){
        this.playable = playable;
    }

    public void setNextSong() {
        System.out.println("Playing Next Song.");
    }

    public void setPreviousSong() {
        System.out.println("Playing previous Song.");
    }

    public void startPlaying() {
        System.out.println("Started playing song.");
    }

    public void stopPlaying() {
        System.out.println("Stopped playing song.");
    }

    public void play() {
        ui.play();
    }

    public void pause() {
        ui.pause();
    }

    public void next() {
        ui.next();
    }

    public void previous() {
        ui.previous();
    }


}
