package com.trendcore.concurrent;

import java.util.Random;

/**
 * Created by Anurag
 */
public class QueryExecutor implements Runnable {

    private final String threadname;
    ConnectionPool pool = new ConnectionPool();
    private boolean running;

    public QueryExecutor(String s, ConnectionPool pool) {
        this.pool = pool;
        running = true;
        threadname = s;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {

        while (running) {

            try {
                Object connection = pool.getConnection();

                //System.out.println("Thread :- " + threadname + " Received Connection :- " + connection);

                doTask();

                pool.returnConnection(connection);

            } catch (Exception e) {
                e.printStackTrace();
                //System.out.println(threadname + " Exception from pool "+ e.getMessage());
                doTask();
            }
        }

    }

    private void doTask() {
        try {
            Thread.sleep(500+new Random().nextInt(150));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
