package com.trendcore.singleton;

/**
 * Created by Anurag
 */
public class SingletonClass implements Cloneable{

    private static SingletonClass  singletonClass;

    private SingletonClass(){}

    public static SingletonClass getInstance(){
        if(singletonClass == null){
            singletonClass = new SingletonClass();
        }
        return singletonClass;
    }

    public void method1(){
        System.out.println("Method 1");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
