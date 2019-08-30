package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of
 * all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum m = new MinimumPathSum();

        /*m.testCase(new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        }, 7);

        m.testCase(new int[][]{
                {1,1,1},
                {1,1,1},
                {4,1,1}
        }, 5);*/

        m.testCase(new int[][]{
                {1,2,5},
                {3,2,1}
        }, 5);

    }

    private void testCase(int[][] ints, int i) {
        int i1 = minPathSum(ints);
        System.out.println(" Actual :- " + i1 + " expected :- " + i);
    }

    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int output[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    output[i][j] = grid[i][j];
                } else {
                    if (i == 0) {
                        output[i][j] = grid[i][j] + output[i][j-1];
                    } else if (j == 0) {
                        output[i][j] = grid[i][j] + output[i-1][j];
                    } else {
                        int rightPathWeight = output[i-1][j];
                        int topPathWeight = output[i][j-1];
                        if(rightPathWeight < topPathWeight){
                            output[i][j] = grid[i][j] + rightPathWeight;
                        }else{
                            output[i][j] = grid[i][j] + topPathWeight;
                        }
                    }
                }
            }
        }

        return output[m-1][n-1];
    }
}
