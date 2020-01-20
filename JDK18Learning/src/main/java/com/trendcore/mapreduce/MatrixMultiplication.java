package com.trendcore.mapreduce;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MatrixMultiplication {

    public static void main(String[] args) {
        Integer a[][] = {
                {1, 2, 3},
                {4, 5, 6}
        };

        Integer b[][] = {
                {6,3},
                {5,2},
                {4,1}
        };

        Arrays.asList(a, b)
                .stream()
                .flatMap(ints -> Arrays.stream(ints))
                .flatMap(integers -> Arrays.stream(integers))
                .forEach(integer -> System.out.println(integer));


    }

}
