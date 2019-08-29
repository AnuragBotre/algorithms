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
        }, 2);

        u.testCase(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }, 6);

        u.testCase(new int[][]{
                {0, 0, 1},
                {0, 0, 0},
                {0, 0, 0}
        }, 5);

        u.testCase(new int[][]{
                {0, 0, 1},
                {0, 1, 0},
                {0, 0, 0}
        },1);

        u.testCase(new int[][]{
                {0, 0, 0}
        },1);

        u.testCase(new int[][]{
                {0},
                {0},
                {0}
        },1);

        u.testCase(new int[][]{
                {0, 1, 0}
        }, 0);

        u.testCase(new int[][]{
                {0},
                {1},
                {0}
        }, 0);

        u.testCase(new int[][]{
                {0, 0},
                {1, 0},
                {0, 0}
        }, 1);

        u.testCase(new int[][]{
                {0, 0 , 0},
                {1, 0 , 0}
        }, 2);

    }

    private void testCase(int[][] obstacleGrid, int expected) {
        int i = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(" Possible ways :- " + i + " expected :- " + expected);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int finalCount = 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int previousCount = 0;

        int output[][] = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int i = 0; i < m; i++) {

            boolean flag = false;

            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {

                    int x = 0;
                    int y = 0;

                    if (i == 0 && j == 0) {
                        if (obstacleGrid[i][j] == 1) {
                            flag = true;
                            break;
                        }else{
                            output[i][j] = 1;
                        }
                    } else {
                        if (i == 0) {
                            if (obstacleGrid[i][j] == 1) {
                                output[i][j] = 0;
                            } else {
                                if (output[i][j - 1] != 0)
                                    output[i][j] = 1;
                                else
                                    output[i][j] = 0;
                            }
                        } else if (j == 0) {
                            if (obstacleGrid[i][j] == 1) {
                                output[i][j] = 0;
                            } else {
                                if (output[i - 1][j] != 0)
                                    output[i][j] = 1;
                                else
                                    output[i][j] = 0;
                            }
                        }
                    }

                } else {

                    int x = 0;
                    if (output[i - 1][j] != -1) {
                        x = output[i - 1][j];
                    }

                    int y = 0;
                    if (output[i][j - 1] != -1) {
                        y = output[i][j - 1];
                    }

                    output[i][j] = x + y;

                    if (obstacleGrid[i][j] == 1) {
                        output[i][j] = 0;
                    }
                    /*output[i][j] = output[i - 1][j] + output[i][j - 1];
                    if (obstacleGrid[i][j] == 1) {
                        output[i][j] = 0;
                    }*/
                    debug(output, i, j, i - 1, j - 1);
                }
            }

            if (flag) {
                break;
            }
        }

        //System.out.println(output[m - 1][n - 1]);

        return output[m - 1][n - 1];
    }

    private boolean obstacleIsPresentInCol(int[][] obstacleGrid, int i, int j) {

        if (j == 0) {
            for (int k = i; k < obstacleGrid.length; k++) {
                if (obstacleGrid[k][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean obstacleShouldNotBePresentInRowOrCol(int[][] obstacleGrid, int i, int j) {
        if (i == 0 && j == 0) {
            if (obstacleGrid[i][j] == 1) {
                return false;
            }
        }

        if (i == 0) {
            for (int k = 0; k < obstacleGrid[i].length; k++) {
                if (obstacleGrid[i][k] == 1)
                    return false;
            }
        }

        if (j == 0) {
            for (int k = 0; k < obstacleGrid.length; k++) {
                if (obstacleGrid[k][j] == 1)
                    return false;
            }
        }

        return true;
    }

    private void debug(int[][] output, int i, int j, int i1, int j1) {
        /*System.out.println(" i = " + i + " j = " + j);
        System.out.println(" i - 1 = " + i1 + " j - 1 = " + j1);
        printMatrix(output);*/
    }

    private void debug(int[][] output, int i, int j) {
        /*System.out.println(" i = " + i + " j = " + j);
        printMatrix(output);*/
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
