package com.trendcore;

/**
 * Created by Anurag
 */
public class ExecuteAround {

    public static void main(String[] args) {
        /**
         * Resouce can be of any type like Database Connection.
         */
        Java7Resouce resouce = new Java7Resouce();
        resouce.op1();
        resouce.op2();
        /**
         * Close method needs to be called explicitly.
         * What if someone forgets to call close method
         */
        resouce.close();

        /**
         * This is also called
         * ARM - Automatic Resouce Management
         * or try with resouce.
         */
        try(Java7Resouce resouce1 = new Java7Resouce()){
            resouce1.op1();
            resouce1.op1();
        }

        /**
         * Using Lambdas Consumer Inteface which is from
         * family of function interface.
         */

        Java8Resouce.use(java8Resouce -> {
            java8Resouce.op1();
            java8Resouce.op2();
        });
    }

}
