package com.trendcore;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Anurag
 */
public class Java8Resouce {

    private Java8Resouce() {
        System.out.println("Creating Java 8 resouce.");
    }

    public void op1() {
        System.out.println("Executing Operation 1");
    }

    public void op2() {
        System.out.println("Executing Operation 2");
    }

    private void close() {
        System.out.println("closing resouce");
    }

    public static void use(Consumer<Java8Resouce> consumer) {
        Java8Resouce resouce = new Java8Resouce();
        try {
            consumer.accept(resouce);
        }finally {
            resouce.close();
        }
    }
}
