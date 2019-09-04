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

    @Test
    public void for_faileInput1() {


        act(new int[][]{
                {3, 5, 5, 6, 9, 1, 4, 5, 0, 5},
                {2, 7, 9, 5, 9, 5, 4, 9, 6, 8},
                {6, 0, 7, 8, 1, 0, 1, 6, 8, 1},
                {7, 2, 6, 5, 8, 5, 6, 5, 0, 6},
                {2, 3, 3, 1, 0, 4, 6, 5, 3, 5},
                {5, 9, 7, 3, 8, 8, 5, 1, 4, 3},
                {2, 4, 7, 9, 9, 8, 4, 7, 3, 7},
                {3, 5, 2, 8, 8, 2, 2, 4, 9, 8}
        }, new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 9, 5, 0, 0, 4, 9, 0, 8},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 0, 7, 3, 0, 0, 5, 1, 0, 3},
                {2, 0, 7, 9, 0, 0, 4, 7, 0, 7},
                {3, 0, 2, 8, 0, 0, 2, 4, 0, 8}
        });
    }

    public void act(int[][] matrix, int[][] expected) {
        setMatrixZeroes.setZeroes(matrix);

        boolean notValid = false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            StringBuilder errorLocation = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
                try {
                    assertEquals(expected[i][j], matrix[i][j]);
                } catch (Throwable e) {
                    notValid = true;
                    errorLocation.append(" i,j -> " + i + "," + j);
                    errorLocation.append("\r\n");
                }
            }

            System.out.println();
            String errorLocationString = errorLocation.toString();
            if(!errorLocationString.isEmpty())
                System.out.println(errorLocationString);

        }

        assertTrue(!notValid);

    }
}