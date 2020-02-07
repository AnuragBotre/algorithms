package com.trendcore.problems.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LargestRectangleInHistogramTest {

    LargestRectangleInHistogram l = new LargestRectangleInHistogram();

    @Test
    public void input1() {
        act(toArr(2, 1, 5, 6, 2, 3), 10);
    }

    private int[] toArr(int... args) {
        return args;
    }

    private void act(int[] nums, int expected) {
        int output = l.largestRectangleArea(nums);
        assertEquals(expected, output);
    }
}