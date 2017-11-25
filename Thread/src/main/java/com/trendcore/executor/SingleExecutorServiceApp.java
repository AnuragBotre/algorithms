package com.trendcore.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Anurag
 */
public class SingleExecutorServiceApp {

    public static void main(String[] args) {
        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();

        /*Future<?> result = singleExecutorService.submit(new SingleExecutorThread());
        singleExecutorService.submit(new SingleExecutorThread());
        singleExecutorService.submit(new SingleExecutorThread());*/


        List list = new ArrayList();
        SingleExecutorCallable singleExecutorCallable = new SingleExecutorCallable();
        list.add(singleExecutorCallable);
        try {
            //List list1 = singleExecutorService.invokeAll(list);

            Future submit = singleExecutorService.submit(singleExecutorCallable);

            System.out.println(submit.isDone());

            System.out.println(submit.get());

            //System.out.println(list1.get(0));
            /*for(Object o : list1){
                System.out.println(((Future)o).get());
            }*/
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/ catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally{

        }


        /*singleExecutorService.shutdown();

        singleExecutorService.submit(new SingleExecutorThread());
        singleExecutorService.submit(new SingleExecutorThread());*/


        /*for(int i = 0 ; i < 10000 ; i++ ){
            System.out.println("From Main Thread " + i);
            sleep();
        }*/

        System.out.println("Exiting Main.");
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
