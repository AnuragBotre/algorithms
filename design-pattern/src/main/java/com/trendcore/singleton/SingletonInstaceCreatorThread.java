package com.trendcore.singleton;

/**
 * Created by Anurag
 */
public class SingletonInstaceCreatorThread implements Runnable {

    @Override
    public void run() {
        sleep(100);
        SingletonWithDoubleCheckLokcing instance = SingletonWithDoubleCheckLokcing.getInstance();
        ThreadLocal t = new ThreadLocal();
        t.set(instance);
        sleep(100);
        System.out.println(Thread.currentThread().getName()+t.get());
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
