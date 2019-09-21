package com.trendcore.problems.leetcode;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SubsetsTest {

    Subsets subsets = new Subsets();

    @Test
    public void input_1() {
        List<List<Integer>> subsets = this.subsets.subsets(new int[]{1, 2, 3});

        subsets.stream().forEach(integers -> {
            System
                .out
                .println("["+integers
                                .stream()
                                .map(integer -> ""+integer)
                                .collect(Collectors.joining(","))+"]");
        });
    }
}