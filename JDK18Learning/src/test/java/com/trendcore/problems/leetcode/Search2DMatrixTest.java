package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class Search2DMatrixTest {

    Search2DMatrix search2DMatrix = new Search2DMatrix();

    @Test
    public void example_1() {
        act(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 3, true);
    }

    @Test
    public void example_2() {
        act(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 13, false);
    }

    @Test
    public void example_3() {
        act(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 10, true);
    }

    @Test
    public void single_row() {
        act(new int[][]{
                {1, 3, 5, 7},
        }, 7, true);
    }

    @Test
    public void single_col() {
        act(new int[][]{
                {1},
                {3},
                {5},
                {7}
        }, 7, true);
    }

    @Test
    public void targetPresentInLastRow() {
        act(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 34, true);
    }

    @Test
    public void blankMatrix() {
        act(new int[][]{
        }, 34, false);
    }

    @Test
    public void matrixWithBlankRow() {
        act(new int[][]{
                {}
        }, 34, false);
    }

    @Test
    public void withSingleInput() {
        act(new int[][]{
                {1}
        }, 0, false);
    }

    @Test
    public void withSingleInput_1() {
        act(new int[][]{
                {1}
        }, 1, true);
    }

    public void act(int[][] matrix, int target, boolean expected) {
        boolean b = search2DMatrix.searchMatrix(matrix, target);
        assertEquals(expected, b);
    }
}