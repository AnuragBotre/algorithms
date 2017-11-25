package com.trendcore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Anurag
 */
public class FunctionInterfaceMap {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        int factor = 2;

        //variables used in lambda should be final or effectively final.

        Stream<Integer> integerStream = list.stream().map(integer -> integer * factor);

        //System.out.println(integerStream);
        //factor = 44;


        integerStream.forEach(System.out::println);
    }
}
