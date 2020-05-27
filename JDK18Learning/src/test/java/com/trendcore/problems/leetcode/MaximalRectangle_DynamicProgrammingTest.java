package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximalRectangle_DynamicProgrammingTest {

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
                {'1', '1'},
                {'1', '1'},
        }, 4);
    }

    @Test
    public void input4() {
        act(new char[][]{
                {'0', '1'},
                {'1', '1'},
        }, 2);
    }

    @Test
    public void input3() {
        act(new char[][]{
                {'0', '1', '1'},
                {'1', '1', '1'},
        }, 4);
    }

    @Test
    public void oneRow_Input1() {
        act(new char[][]{
                {'1', '0', '1', '0', '0'}
        }, 1);
    }

    @Test
    public void onerow_input2() {
        act(new char[][]{
                {'1', '0', '1', '1', '0'}
        }, 2);
    }

    @Test
    public void onerow_input3() {
        act(new char[][]{
                {'1', '0', '1', '1', '1'}
        }, 3);
    }

    @Test
    public void onecolumn_input1() {
        act(new char[][]{
                {'1'},
                {'0'},
                {'0'}
        }, 1);
    }

    @Test
    public void onecolumn_input2() {
        act(new char[][]{
                {'1'},
                {'0'},
                {'1'},
                {'1'}
        }, 2);
    }

    @Test
    public void onecolumn_input3() {
        act(new char[][]{
                {'1'},
                {'0'},
                {'1'},
                {'1'},
                {'1'},
                {'0'}
        }, 3);
    }

    private void act(char[][] strings, int expected) {
        MaximalRectangle_DynamicProgramming m = new MaximalRectangle_DynamicProgramming();
        int output = m.maximalRectangle(strings);
        assertEquals(expected, output);
    }

    @Test
    public void testLargestHistogram() {
        MaximalRectangle_DynamicProgramming m = new MaximalRectangle_DynamicProgramming();
        int largestHistogram = m.findLargestHistogram(new int[]{1, 2, 3, 4, 3, 2, 1});
        assertEquals(10, largestHistogram);
    }

    @Test
    public void testHistogram() {
        MaximalRectangle_DynamicProgramming m = new MaximalRectangle_DynamicProgramming();
        int largestHistogram = m.findLargestHistogram(new int[]{2, 0, 2, 1, 1});
        //int largestHistogram = m.findLargestHistogram(new int[]{1,2,4});
        assertEquals(3, largestHistogram);
    }

    @Test
    public void testHistogram_1() {
        MaximalRectangle_DynamicProgramming m = new MaximalRectangle_DynamicProgramming();
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();
        int largestHistogram = m.findLargestHistogram(new int[]{2, 5, 3, 4, 1});
        //int largestHistogram = l.largestRectangleArea(new int[]{2, 5, 3, 4, 1});
        assertEquals(9, largestHistogram);
    }

    @Test
    public void testHistogram1() {
        MaximalRectangle_DynamicProgramming m = new MaximalRectangle_DynamicProgramming();
        int largestHistogram = m.findLargestHistogram(new int[]{3, 2, 2, 1, 1});
        //int largestHistogram = m.findLargestHistogram(new int[]{1,2,4});
        assertEquals(6, largestHistogram);
    }

    @Test
    public void failedInput1(){
        act(new char[][]{
                {'0','1','1','0','1'},
                {'1','1','0','1','0'},
                {'0','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'}}, 9);
    }
}