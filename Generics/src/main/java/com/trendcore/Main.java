package com.trendcore;

/**
 * Created by Anurag
 */
public class Main {

    public static void m(Object o){
        System.out.println("Object" + o);
    }

    public static void m(String o){
        System.out.println(o);
    }

    public static void main(String[] args) {
        m("abc");
    }

}
