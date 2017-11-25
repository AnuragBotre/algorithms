package com.trendcore.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This application is kind of producer consumer problem
 *
 * In this example QueryExecutor class will get connection from connection pool.
 * Connection pool will keep on modifying list.
 * After getting connection it removes connection from the list.
 * After receiving connection it add connection to the list.
 * Hence getting connection and adding it to pool is done under lock.
 *
 * Created by Anurag
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ConnectionPool pool = new ConnectionPool();
        pool.initialize(1);
        pool.setAwakeWaitingThread(true);


        List<Thread> listOfThreads = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Thread connectionRetriever = new Thread(new QueryExecutor("Thread-"+(i+1),pool));
            connectionRetriever.setName("Thread-"+(i+1));
            connectionRetriever.start();
            listOfThreads.add(connectionRetriever);
        }

        boolean flag = true;
        while(flag){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int option = Integer.parseInt(reader.readLine());
            switch (option){
                case 1:
                    pool.setAwakeWaitingThread(true);
                    break;
                case 2:
                    pool.setAwakeWaitingThread(false);
                    break;
                case 3:
                    printWaitingThreads(listOfThreads);
                    break;
                case 4:
                    printBlockedThreads(listOfThreads);
                    break;
                case 5:
                    pool.sendSignalToWaitingThread();
                    break;
                case 6:
                    flag = false;
                    break;
            }
        }
    }

    private static void printWaitingThreads(List<Thread> listOfThreads) {
        printThreadState(listOfThreads, Thread.State.WAITING);
    }

    private static void printBlockedThreads(List<Thread> listOfThreads) {
        printThreadState(listOfThreads, Thread.State.BLOCKED);
    }

    private static void printThreadState(List<Thread> listOfThreads, Thread.State STATE) {
        for(Thread thread : listOfThreads){
            if(STATE.equals(thread.getState())){
                System.out.println("Thread is waiting " + thread.getName());
            }
        }
    }
}
