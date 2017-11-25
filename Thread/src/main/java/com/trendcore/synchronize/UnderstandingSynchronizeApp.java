package com.trendcore.synchronize;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class UnderstandingSynchronizeApp {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("123");
        Thread writerThread = new Thread(new WriterThread(list));
        writerThread.start();

        Thread readThread1 = new Thread(new ReaderThread(list));
        Thread readThread2 = new Thread(new ReaderThread(list));
        Thread readThread3 = new Thread(new ReaderThread(list));

        sleep(100);

        readThread1.start();
        readThread2.start();
        readThread3.start();
    }

    private static class WriterThread implements Runnable {

        private final List list;

        public WriterThread(List list) {
            this.list = list;
        }

        @Override
        public void run() {

            boolean flag = true;

            while(flag){

                synchronized (list){
                    System.out.println("Writer Thread Acquire lock...");
                    sleep(1000);

                    System.out.println("Writer Thread Adding element...");
                    list.add(0,"456");
                }
            }


        }
    }

    private static class ReaderThread implements Runnable {

        private List list;

        public ReaderThread(List list) {
            this.list = list;
        }

        @Override
        public void run() {
            boolean flag = true;
            while (flag){
                System.out.println(Thread.currentThread().getName() + " " + list.get(0));

                sleep(100);
            }
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
