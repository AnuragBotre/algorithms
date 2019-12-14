package com.trendcore;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionComposition {

    @Test
    public void addWithAndThen() {
        Function<Integer, Integer> function = o -> {
            System.out.println("executed first");
            return o;
        };

        Function<Integer, Integer> function1 = o -> {
            System.out.println("executed second");
            return o + 1;
        };

        Integer apply = function1.andThen(function).apply(2);

        System.out.println(apply);
    }

    @Test
    public void addWithCompose() {
        Function<Integer, Integer> function = o -> {
            System.out.println("executed first");
            return o;
        };

        Function<Integer, Integer> function1 = o -> {
            System.out.println("executed second");
            return o + 1;
        };

        Integer apply = function1.compose(function).apply(2);

        System.out.println(apply);
    }

    @Test
    public void noMoreException() {

        File file = new File("D:\\Anurag\\export.json");

        Consumer<InputStream> fileProcessor = stream -> {
            //throw new RuntimeException();
        };

        Either either = processFile(file, fileProcessor);

        if (either.exception != null) {
            Arrays.asList(either.exception.getStackTrace()).stream().forEach(System.out::println);
        }

        System.out.println("I am going to execute.");
    }

    class Either {
        Object block;
        Exception exception;
    }

    private Either processFile(File file, Consumer<InputStream> fileProcessor) {
        Either either = new Either();
        try {
            FileInputStream fis = new FileInputStream(file);
            fileProcessor.accept(fis);
        } catch (Exception e) {
            either.exception = e;
        }
        return either;
    }
}
