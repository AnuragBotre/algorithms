package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * 74. Search a 2D Matrix
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * Example 2:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0) {
            return false;
        } else if(matrix[0].length == 0){
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int rowToBeSearchedIn = rows - 1;

        for (int i = 0; i < rows; i++) {

            //first find out the row
            if (matrix[i][0] > target) {

                if(i == 0){
                    return matrix[i][0] == target;
                }

                rowToBeSearchedIn = i - 1;
                break;
            }
        }

        for (int j = 0; j < cols; j++) {
            if (matrix[rowToBeSearchedIn][j] == target) {
                return true;
            }
        }

        return false;
    }
}
