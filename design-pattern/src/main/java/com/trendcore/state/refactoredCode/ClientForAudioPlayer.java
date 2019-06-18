package com.trendcore.state.refactoredCode;

public class ClientForAudioPlayer {

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        player.play();
        player.pause();
        player.next();
        player.next();
        player.play();
        player.previous();
    }

}
