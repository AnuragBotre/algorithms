package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximalRectangleTest {

    MaximalRectangle m = new MaximalRectangle();

    @Test
    public void input1() {
        act(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }, 6);
    }

    private void act(char[][] strings, int expected) {
        int output = m.maximalRectangle(strings);
        assertEquals(expected, output);
    }
}