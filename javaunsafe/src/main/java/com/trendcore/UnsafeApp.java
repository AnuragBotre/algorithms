package com.trendcore;

import org.openjdk.jol.info.GraphLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Scanner;

public class UnsafeApp {

    private static Unsafe unsafe;

    public static Unsafe getUnsafe() throws Exception {
        if(unsafe == null){
            Field f = null;
            try {
                f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new Exception("Unable to get Unsafe");
            }

        }
        return unsafe;
    }

    public static void main(String[] args) throws Exception {


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

    public static void copyMemory() {

    }

    static Object shallowCopy(Object obj) throws Exception {
        long size = sizeOf(obj);
        long start = toAddress(obj);
        long address = getUnsafe().allocateMemory(size);
        getUnsafe().copyMemory(start, address, size);
        return fromAddress(address);
    }

    static long toAddress(Object obj) throws Exception {
        Object[] array = new Object[] {obj};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        return normalize(getUnsafe().getInt(array, baseOffset));
    }

    static Object fromAddress(long address) throws Exception {
        Object[] array = new Object[] {null};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }


    public static long sizeOf(Object o) throws Exception {
        Unsafe u = getUnsafe();
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        return ((maxSize/8) + 1) * 8;   // padding
    }

    private static long normalize(int value) {
        if(value >= 0) return value;
        return (~0L >>> 32) & value;
    }
}
