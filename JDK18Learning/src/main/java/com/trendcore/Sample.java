package com.trendcore;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Anurag
 */
public class Sample {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        //External Iterator
        for(int i : list){
            System.out.println(i);
        }

        //internal interator
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        //method with value
        list.forEach((Integer integer) -> {
            System.out.println(integer);
            System.out.println("doing other stuff");
        });

        /**
         * Functional programming.
        * */
        list.forEach((integer) -> {
            System.out.println(integer);
            System.out.println("doing other stuff");
        });

        /**
         * Passing static methods
         * */
        list.forEach(System.out::print);

        Sample s = new Sample();
        /**
         * Passing non static methods
         * */
        list.forEach(s::nonStaticMethod);

    }

    public void nonStaticMethod(Integer integer){
        System.out.println("Inside non static method " + integer);
    }
}
