package com.trendcore;

/**
 * Created by Anurag on 4/25/2017.
 */
public class ModelRequestCreator implements Runnable{

    @Override
    public void run() {
        final String state = "State :- " + System.currentTimeMillis();

        System.out.println("Creating State :- " + state);

        Action action = new Action() {
            @Override
            public Object execute() {
                System.out.println("Retrieved state :- " + state);
                return "Return object from action";
            }
        };
        ModelManagerWrapper.getInstance().createModel(action);
    }
}
