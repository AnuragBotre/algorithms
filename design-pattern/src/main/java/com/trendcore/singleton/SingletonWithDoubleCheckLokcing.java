package com.trendcore.singleton;

/**
 * Created by Anurag
 */
public class SingletonWithDoubleCheckLokcing {

    private static SingletonWithDoubleCheckLokcing instance;

    private SingletonWithDoubleCheckLokcing(){

    }

    /**
     * Implementation - 1
     * In case of Multi threaded this will break
     * Output after multiple runs
     *
     Run-1
            Thread-Thread-2
            Thread-Thread-0
            Thread-Thread-1
            Thread-Thread-3
     Run-2
            Thread-Thread-0
     Run-2
            Thread-Thread-3
     Run-4
             Thread-Thread-0
             Thread-Thread-4
             Thread-Thread-2
     *
     * */
    /*public static SingletonWithDoubleCheckLokcing getInstance(){
        if(instance == null){
            instance = new SingletonWithDoubleCheckLokcing();
            System.out.println("Thread-" + Thread.currentThread().getName()+instance);
        }
        return instance;
    }*/

    /**
     * Implementation - 2
     * In case of Multi threaded this will break
     * Output after multiple runs
     *
     Run-1
     Thread-Thread-2
     Thread-Thread-0
     Thread-Thread-1
     Thread-Thread-3
     Run-2
     Thread-Thread-0
     Run-2
     Thread-Thread-3
     Run-4
     Thread-Thread-0
     Thread-Thread-4
     Thread-Thread-2
     *
     * */
    /*public static SingletonWithDoubleCheckLokcing getInstance(){
        if(instance == null){
            synchronized (SingletonClass.class){
                instance = new SingletonWithDoubleCheckLokcing();
                System.out.println("Thread-" + Thread.currentThread().getName()+instance);
            }
        }
        return instance;
    }*/

    /**
     * Implementation - 3 Using Volatile
     * In case of Multi threaded this will not break
     * Output after multiple runs
     *
     Run-1
     Thread-Thread-2
     Thread-Thread-0
     Thread-Thread-1
     Thread-Thread-3
     Run-2
     Thread-Thread-0
     Run-2
     Thread-Thread-3
     Run-4
     Thread-Thread-0
     Thread-Thread-4
     Thread-Thread-2
     *
     * */
    /*public static SingletonWithDoubleCheckLokcing getInstance(){
        if(instance == null){
            synchronized (SingletonClass.class){
                instance = new SingletonWithDoubleCheckLokcing();
                System.out.println("Thread-" + Thread.currentThread().getName()+ instance);
            }
        }
        return instance;
    }*/


    /**
     * Implementation - 4
     * In case of Multi threaded this will not break
     * Its optimization of approach-3
     * Output after multiple runs
     *
     Run-1
     Thread-Thread-2

     Run-2
     Thread-Thread-0
     Run-2
     Thread-Thread-3
     Run-4
     Thread-Thread-0

     Always Single Thread will initialize the instance
     *
     * */
    public static SingletonWithDoubleCheckLokcing getInstance(){
        if(instance == null){
            synchronized (SingletonClass.class){
                if(instance == null){
                    instance = new SingletonWithDoubleCheckLokcing();
                    System.out.println("Thread-" + Thread.currentThread().getName()+ instance);
                }
            }
        }

        return instance;
    }

}
