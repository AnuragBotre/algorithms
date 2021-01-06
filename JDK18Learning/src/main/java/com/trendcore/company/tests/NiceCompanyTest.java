package com.trendcore.company.tests;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NiceCompanyTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock  = new ReentrantLock();

        //problem - 1
        //look for the syntax
        lock.tryLock(100, TimeUnit.MICROSECONDS);
        lock.lock();

        /*
            which will give compilation error

            Lock lock  = new ReentrantLock();

            lock.tryLock(100, TimeUnit.MICROSECONDS); // 1

            try {
                lock.lock(100, TimeUnit.MICROSECONDS); // 2
            } catch (Exception e) {
                sout("exception")
            } finally {
                sout("finally")
            }


            Options :-
            1. compilation error at 1
            2. compilation error at 2
            3. sout("exception")
            4. sout("finally")
         */


        //problem - 2
        //Spring - how to create bean using factory method

        //problem - 3
        //Spring - how to create proxy bean for created bean

        //


    }

}
