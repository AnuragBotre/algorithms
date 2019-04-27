package com.trendcore.problems.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-paths/
 * <p>
 * 62. Unique Paths
 * <p>
 * A robot is located at the top-left corner of a m x n grid
 * (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid
 * (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * <p>
 * Input: m = 7, n = 3
 * Output: 28
 */
public class UniquePaths {

    int count = 0;

    public static void main(String[] args) {
        UniquePaths paths = new UniquePaths();
        paths.testCase(3, 2);
        paths.testCase(3, 3);
        /*paths.testCase(7, 3);
        paths.testCase(23, 12);*/
    }

    private void testCase(int i, int i1) {
        int i2 = uniquePaths(i, i1);
        System.out.println("i2 = " + i2);
    }

    public int uniquePaths(int m, int n) {

        int i = oneOfLeetCodeApproach(m, n);
        return i;
    }

    private int traverse(int m, int n) {
        //traverse down and traverse right
        Set paths = new HashSet();
        String pathString = "";
        count = 0;
        traverseInternal(0, 0, m, n, paths, pathString);
        return count;
    }

    private void traverseInternal(int m1, int n1, int m, int n, Set paths, String pathString) {
        if (m1 >= m) {
            return;
        }
        if (n1 >= n) {
            return;
        }

        if (pathString == "") {
            //pathString = m1 + "," + n1;
        } else {
            //pathString = pathString + ":" + m1 + "," + n1;
        }

        traverseInternal(m1 + 1, n1, m, n, paths, pathString);
        traverseInternal(m1, n1 + 1, m, n, paths, pathString);

        if (m1 == m - 1 && n1 == n - 1) {
            //System.out.println(pathString);
            //paths.add(pathString);
            count++;
        }

    }

    public int oneOfLeetCodeApproach(int m, int n) {
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0)
                    res[i][j] = 1;
                else
                    res[i][j] = res[i-1][j] + res[i][j-1];

                //printArr(res);
            }
        }
        return res[m-1][n-1];
    }

    private void printArr(int[][] res) {
        for(int i = 0 ; i < res[0].length ; i++){
            for(int j = 0 ; j < res.length ; j++){
                System.out.print(res[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}