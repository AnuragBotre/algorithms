package com.trendcore.lock;

/**
 * Created by Anurag
 */
public class Semaphore {

    private final Integer upperBound;

    private int current;

    public Semaphore(int upperBound){
        this.upperBound = upperBound;
        this.current = 0;
    }

    public Semaphore(){
        this.upperBound = 2;
        this.current = 0;
    }


    public void take() throws InterruptedException {
        synchronized (this){
            while(current == upperBound){
                System.out.println("Thread " + Thread.currentThread().getName() + " waiting for semaphore.");
                wait();
            }

            //This will not work
            /*if(current == upperBound){
                System.out.println("Thread " + Thread.currentThread().getName() + " waiting for semaphore.");
                wait();
            }*/

            System.out.println("Thread " + Thread.currentThread().getName() + " acquiring semaphore.");
            current++;

        }
    }

    public void relase() throws InterruptedException {
        synchronized (this){
            /*while(current == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            /*if(current < 0){
                wait();
            }*/

            System.out.println("Thread " + Thread.currentThread().getName() + " releasing semaphore.");
            current--;
            notifyAll();
        }
    }


    public int getCurrent() {
        return current;
    }
}
