package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximalRectangleTest {

    @Test
    public void input1() {
        act(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }, 6);
    }

    @Test
    public void input2() {
        act(new char[][]{
                {'1', '1', '0',},
                {'1', '1', '0',},
                {'1', '1', '0',},
                {'0', '0', '0',}
        }, 6);
    }

    @Test
    public void input3() {
        act(new char[][]{
                {'1', '0', '0',},
                {'1', '1', '1',},
                {'0', '0', '0',},
        }, 3);
    }

    @Test
    public void input4() {
        act(new char[][]{
                {'1', '1', '1',}
        }, 3);
    }

    @Test
    public void input5() {
        act(new char[][]{
                {'1'},
                {'1'},
                {'1'}
        }, 3);
    }

    private void act(char[][] strings, int expected) {
        MaximalRectangle m = new MaximalRectangle();
        int output = m.maximalRectangle(strings);
        assertEquals(expected, output);
    }
}