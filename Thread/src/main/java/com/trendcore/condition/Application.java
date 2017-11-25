package com.trendcore.condition;


/**
 * Created by Anurag
 */
public class Application {

    public static void main(String[] args) {
        ShareObject shareObject = new ShareObject();

        Thread incrementThread = new Thread(new IncrementThread(shareObject));
        Thread decrementThread = new Thread(new DecrementThread(shareObject));

        incrementThread.start();
        decrementThread.start();
    }

}
