package com.trendcore.join;

/**
 * Created by Anurag
 */
public class SimpleThread implements Runnable{

    @Override
    public void run() {
        for(int i = 0 ; i < 5 ; i++){
            System.out.println(" Inside Thread :- "+Thread.currentThread().getName()+" Calculating values :- " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }
}
