package com.trendcore;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        ModelManagerWrapper.getInstance().initializeModelManager();

        for(int i = 0 ; i < 10 ; i++){
            Thread t = new Thread(new ModelRequestCreator());
            t.start();
        }
    }
}
