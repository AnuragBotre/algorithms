package com.trendcore.concurrent.multilock;

/**
 * Created by Anurag
 */
public class MultiLockThreadWithSameData implements Runnable {

    private final SharedDataStructure s;

    public MultiLockThreadWithSameData(SharedDataStructure s) {
        this.s = s;
    }

    @Override
    public void run() {
        boolean flag = true;
        while(flag){
            s.getLock().lock();
            s.getLock().lock();
            s.getLock().lock();

            try{

                System.out.println("Thread "+Thread.currentThread().getName()+" Incrementing Datastructure1 counter :- " + s.incrementCounter());
                System.out.println("Thread "+Thread.currentThread().getName()+" Incrementing Datastructure1 counter :- " + s.decrementCounter());

            }finally {
                s.getLock().unlock();
                s.getLock().unlock();
                s.getLock().unlock();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }
}
