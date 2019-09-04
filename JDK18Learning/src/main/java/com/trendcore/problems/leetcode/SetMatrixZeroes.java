package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * <p>
 * 73. Set Matrix Zeroes
 * <p>
 * Given a m x n matrix, if an element is 0,
 * set its entire row and column to 0. Do it in-place.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * Output:
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * Example 2:
 * <p>
 * Input:
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * Output:
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * Follow up:
 * <p>
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int tempRow[] = new int[cols];

        for (int i = 0; i < rows; i++) {

            //i = 0
            if (i == 0) {
                //copy in tempRow
                copyRow(matrix, i, tempRow);
            }


            //is 0 present and at which location

            Object[] objects = rowContainsZero(matrix[i]);

            boolean zeroPresentInRow = (boolean) objects[0];

            if (zeroPresentInRow) {

                //mark entire row 0
                for (int x = 0; x < cols; x++) {

                    if(matrix[i][x] == 0){
                        tempRow[x] = 0;


                        //go for prev row with 0
                        for (int y = i; y >= 0; y--) {
                            matrix[y][x] = 0;
                        }
                    }

                    matrix[i][x] = 0;
                }


            } else {

                //process temp row

                for (int x = 0 ; x < cols ; x++) {
                    if(tempRow[x] == 0){
                        matrix[i][x] = 0;
                    }
                }
            }

        }
    }

    private Object[] rowContainsZero(int[] row) {

        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0) {
                return new Object[]{true, i};
            }
        }
        return new Object[]{false, 0};
    }

    private void copyRow(int[][] matrix, int i, int[] tempRow) {
        for (int x = 0; x < tempRow.length; x++) {
            tempRow[x] = matrix[i][x];
        }
    }

    public void bruteForceHighSpaceComplexity(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int state[][] = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    //we have to make all rows and cols 0
                    for (int x = 0; x < cols; x++) {
                        state[i][x] = 1;
                    }

                    for (int x = 0; x < rows; x++) {
                        state[x][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (state[i][j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
