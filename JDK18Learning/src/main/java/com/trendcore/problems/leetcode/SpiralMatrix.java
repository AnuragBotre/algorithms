package com.trendcore.problems.leetcode;

import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * <p>
 * 54. Spiral Matrix
 * <p>
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        SpiralMatrix s = new SpiralMatrix();
        s.testCase(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12}
            });
    }

    private void testCase(int[][] matrix) {
        spiralOrder(matrix);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        return null;
    }

}
