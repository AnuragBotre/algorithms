package com.trendcore;

/**
 * Created by Anurag
 */
public class InterruptApp {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    /*System.out.println(Thread.currentThread());*/
                    //Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });
        t.setName("Interrupted Thread");
        t.start();

        try{
            t.interrupt();
        }catch (Exception e){
            System.out.println("Here");
        }


        Thread.sleep(2000);

    }

}
