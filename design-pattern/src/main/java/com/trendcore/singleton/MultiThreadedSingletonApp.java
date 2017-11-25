package com.trendcore.singleton;

/**
 * Created by Anurag
 */
public class MultiThreadedSingletonApp {

    public static void main(String[] args) {

        for(int i = 0 ; i < 5 ; i++){
            Thread thread = new Thread(new SingletonInstaceCreatorThread());
            thread.start();
        }

    }

}
