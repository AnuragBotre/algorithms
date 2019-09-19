package com.trendcore.problems.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CombinationsTest {

    Combinations combinations = new Combinations();

    @Test
    public void input_1() {
        List list = new ArrayList();
        List<List<Integer>> combine = combinations.combine(4, 2);
        print(combine);
    }

    public void print(List<List<Integer>> combine) {
        combine.forEach(integers -> {

            String collect = integers.stream()
                    .map(integer -> "" + integer)
                    .collect(Collectors.joining(","));

            System.out.println(collect);

        });
    }

    @Test
    public void input_2() {
        List list = new ArrayList();
        List<List<Integer>> combine = combinations.combine(4, 3);

        //expected output
        /**
         * [[1,2,3],[1,2,4],[1,3,4],[2,3,4]]
         */
        print(combine);
    }

    @Test
    public void input_3() {
        List list = new ArrayList();
        List<List<Integer>> combine = combinations.combine(2, 1);
        print(combine);
    }

    @Test
    public void input_4() {
        List list = new ArrayList();
        List<List<Integer>> combine = combinations.combine(5, 3);

        //expected output
        /**
         * [[1,2,3],[1,2,4],[1,2,5],[1,3,4],[1,3,5],[1,4,5],[2,3,4],[2,3,5],[2,4,5],[3,4,5]]
         */
        print(combine);
    }
}