package com.trendcore;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeApp {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        System.out.println(unsafe);
    }

}
