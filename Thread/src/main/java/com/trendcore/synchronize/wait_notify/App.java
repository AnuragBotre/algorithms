package com.trendcore.synchronize.wait_notify;

import java.io.*;

/**
 * Created by Anurag
 */
public class App {

    public int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public static void main(String[] args) throws IOException {
        App app = new App();

        Thread incrementCounterThread = new Thread(new IncrementThread(app));
        incrementCounterThread.start();

        Thread decrementCounterThread = new Thread(new DecrementThread(app));
        decrementCounterThread.start();

        int choice = 0;
        while(choice != 4){
            Reader reader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            choice = Integer.parseInt(line);
            switch (choice){
                case 1:
                    incrementCounterThread.interrupt();
                    break;
                case 2:
                    decrementCounterThread.interrupt();
                    break;
                default:
                    break;
            }
        }

        System.out.println("Exiting Main Thread.");
    }
}
