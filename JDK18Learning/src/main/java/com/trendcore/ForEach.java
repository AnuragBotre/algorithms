package com.trendcore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anurag
 */
public class ForEach {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        System.out.println(list);

        List<Integer> newList = list.stream()
                .map(integer -> integer + 1)
                .collect(Collectors.toList());


        System.out.println(newList.hashCode());

        System.out.println(list.hashCode());
    }

}
