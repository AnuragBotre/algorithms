package com.trendcore;

/**
 * Created by Anurag
 */
public class ActionThread implements Runnable {

    private Action action;

    public ActionThread(Action action) {
        this.action = action;
    }

    @Override
    public void run() {
        action.execute();
    }
}
