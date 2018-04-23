package com.trendcore;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class InstantiatingClass {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        class A{
            private String a;

            public A(){
                this.a = "ABC";
            }

            public String getA() {
                return a;
            }

            public void setA(String a) {
                this.a = a;
            }
        };

        A a = new A();

        A o = (A) unsafe.allocateInstance(A.class);

        System.out.println(a.getA() + " " + o.getA());
    }

}
