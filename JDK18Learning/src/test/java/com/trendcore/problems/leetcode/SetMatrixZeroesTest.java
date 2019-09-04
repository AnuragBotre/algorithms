package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetMatrixZeroesTest {

    SetMatrixZeroes setMatrixZeroes = new SetMatrixZeroes();

    @Test
    public void withGivenInput() {
        act(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1},
        }, new int[][]{
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1},
        });
    }

    @Test
    public void input_2() {

        act(new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5},
        }, new int[][]{
                {0, 0, 0, 0},
                {0, 4, 5, 0},
                {0, 3, 1, 0},
        });
    }

    public void act(int[][] matrix, int[][] expected) {
        setMatrixZeroes.setZeroes(matrix);

        boolean notValid = false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
                try {
                    assertEquals(expected[i][j], matrix[i][j]);
                } catch (Throwable e) {
                    notValid = true;
                }
            }
            System.out.println();
        }

        assertTrue(!notValid);

    }
}