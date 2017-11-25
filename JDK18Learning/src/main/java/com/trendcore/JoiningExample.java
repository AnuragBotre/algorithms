package com.trendcore;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Anurag
 */
public class JoiningExample {

    public static void main(String[] args) {

        /**
         * Imperative Style
         */
        /*File file = new File("E:\\Anurag");

        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            System.out.print(files[i].getName());
            if (i != files.length - 1){
                System.out.print(",");
            }
        }*/

        File dir = new File("E:\\Anurag");

        File[] children = dir.listFiles();

        if (children != null) {
            String string = Stream
                    .of(children)
                    .map(file -> {
                        System.out.println("getName." + file.getName());
                        return file.getName();
                    })
                    .map(s -> {
                        System.out.println("toUpperCase." + s);
                        return s.toUpperCase();
                    })
                    .collect(Collectors.joining(","));

            System.out.println(string);
        }
    }

}
