package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClimbingStairsTest {

    ClimbingStairs climbingStairs = new ClimbingStairs();

    @Test
    public void testWithGivenInput() {
        execute(2, 2);
        execute(3, 3);
        execute(4, 5);
        execute(5, 8);
    }

    @Test
    public void failedInput() {
        execute(44, 1134903170);

    }

    public void execute(int n, int expected) {
        int i = climbingStairs.climbStairs(n);
        assertEquals(expected,i);
    }
}