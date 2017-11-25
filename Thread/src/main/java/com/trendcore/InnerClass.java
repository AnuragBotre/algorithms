package com.trendcore;

import java.util.ArrayList;

/**
 * Created by Anurag
 */
public class InnerClass {

    public static void main(String[] args) {

        final String state = "This is state";

        final ArrayList list = new ArrayList(1);

        list.add(1);
        list.add(2);
        list.add(3);

        Action action = new Action(){

            @Override
            public void execute() {
                System.out.println(state);
                System.out.println(list);
            }
        };

        Thread actionThread = new Thread(new ActionThread(action));
        Thread anotherActionThread = new Thread(new AnotherActionThread(list));

        anotherActionThread.start();

        actionThread.start();


        list.add(4);
    }

}