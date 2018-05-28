package com.trendcore;

import org.openjdk.jol.info.GraphLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Scanner;

public class UnsafeApp {

    private static Unsafe unsafe;

    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        unsafe = (Unsafe) f.get(null);

        System.out.println(unsafe);

        directMemoryAccess();
    }

    public static void directMemoryAccess() throws Exception {
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
        System.out.println(addressOfGuard + " " + x + " " + (addressOfGuard-x));
        //For example, there is another Guard object in memory located next
        // to current guard object.
        // We can modify its ACCESS_ALLOWED field with the following code
        //unsafe.putInt(guard, 16 +x, 42); // memory corruption

        Scanner scanner = new Scanner(System.in);

        f = anotherGuard.getClass().getDeclaredField("ACCESS_ALLOWED");
        long l = unsafe.objectFieldOffset(f);
        System.out.println(x + " " +l + " " + (x+l));

        GraphLayout.parseInstance(anotherGuard).toImage("/home/anurag/graphlayout");

        String next = scanner.next();
        unsafe.putInt(guard, Long.parseLong(next), 42);
        //unsafe.putInt(anotherGuard, l, 42);

        System.out.println(anotherGuard.giveAccess());
    }

}
