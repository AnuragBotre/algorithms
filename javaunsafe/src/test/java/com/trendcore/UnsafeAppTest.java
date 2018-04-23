package com.trendcore;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class UnsafeAppTest {

    private Unsafe unsafe;

    @Before
    public void setUp() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        unsafe = (Unsafe) f.get(null);
    }

    @Test
    public void gettingUnsafe() throws Exception{
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println(unsafe);
    }

    @Test
    public void instantiateClass() throws Exception {
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

        System.out.println(o + " " + o.hashCode());

        Constructor<?>[] constructors = o.getClass().getConstructors();
        System.out.println(constructors.length);
        for(Constructor c : constructors){
            System.out.println(c);
            Object o1 = c.newInstance(new Object[]{null});
            System.out.println(" " + o1.hashCode());
        }

    }
}
