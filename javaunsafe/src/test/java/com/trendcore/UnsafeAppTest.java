package com.trendcore;

import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.GraphLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Scanner;

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

    @Test
    public void memoryCorruption() throws Exception {
        class Guard{
            private int ACCESS_ALLOWED = 1;

            public boolean giveAccess() {
                return 42 == ACCESS_ALLOWED;
            }
        }

        Guard guard = new Guard();
        System.out.println(guard.giveAccess());   // false, no access

        Guard anotherGuard = new Guard();

        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42); // memory corruption
        System.out.println(guard.giveAccess()); // true, access granted

        long addressOfGuard = MemoryUtility.addressOf(guard, unsafe);
        long x = MemoryUtility.addressOf(anotherGuard, unsafe);
        System.out.println(addressOfGuard + " " + x);
        //For example, there is another Guard object in memory located next
        // to current guard object.
        // We can modify its ACCESS_ALLOWED field with the following code
        //unsafe.putInt(guard, 16 +x, 42); // memory corruption

        Scanner scanner = new Scanner(System.in);

        f = anotherGuard.getClass().getDeclaredField("ACCESS_ALLOWED");
        long l = unsafe.objectFieldOffset(f);
        System.out.println(x + " " +l);


        unsafe.putInt(guard, scanner.nextInt(), 42);
        //unsafe.putInt(anotherGuard, l, 42);

        System.out.println(
                GraphLayout.parseInstance(anotherGuard).toPrintable());



        System.out.println(anotherGuard.giveAccess());
    }
}
