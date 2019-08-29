package com.trendcore.problems.leetcode;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * 63. Unique Paths II
 * <p>
 * A robot is located at the top-left corner of a m x n grid
 * (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid
 * (marked 'Finish' in the diagram below).
 * <p>
 * Now consider if some obstacles are added to the grids.
 * How many unique paths would there be?
 * <p>
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 */
public class UniquePathsII {

    public static void main(String[] args) {
        UniquePathsII u = new UniquePathsII();

        /*u.testCase(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        });*/

        u.testCase(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        });

    }

    private void testCase(int[][] obstacleGrid) {
        int i = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(" Possible ways :- " + i);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int finalCount = 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int previousCount = 0;

        int output[][] = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    output[i][j] = 1;
                    debug(output, i, j);
                } else {
                    output[i][j] = output[i - 1][j] + output[i][j - 1];
                    debug(output, i, j,i-1,j-1);
                }
            }
        }

        System.out.println(output[m - 1][n - 1]);

        return finalCount;
    }

    private void debug(int[][] output, int i, int j, int i1, int j1) {
        System.out.println(" i = " + i + " j = " + j );
        System.out.println(" i - 1 = " + i1 + " j - 1 = " + j1 );
        printMatrix(output);
    }

    private void debug(int[][] output, int i, int j) {
        System.out.println(" i = " + i + " j = " + j);
        printMatrix(output);
    }

    private void printMatrix(int[][] output) {
        System.out.println();
        Stream.of(output).forEach(ints -> {
                    Arrays.stream(ints).forEach(value -> {
                        System.out.print(value + " ");
                    });
                    System.out.println();
                }
        );
    }

}
