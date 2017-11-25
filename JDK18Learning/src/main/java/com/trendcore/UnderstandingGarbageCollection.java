package com.trendcore;

/**
 * Created by Anurag
 */
public class UnderstandingGarbageCollection {

    public static void main(String[] args) {
        UnderstandingGarbageCollection u = new UnderstandingGarbageCollection();
        u.toString();
        u = null;
        System.gc();

        u = new UnderstandingGarbageCollection();
        System.out.println(u);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this);
    }
}
