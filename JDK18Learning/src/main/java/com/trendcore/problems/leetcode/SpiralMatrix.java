package com.trendcore.problems.leetcode;

import java.util.ArrayList;
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
                {9, 10, 11, 12}
        });
    }

    private void testCase(int[][] matrix) {
        spiralOrder(matrix);
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        //state
        int state = 0;
        int rowOffset = 0;

        int col = 0;
        int row = 0;

        List<Integer> list = new ArrayList<>();

        boolean flag = true;

        for (int i = 0; i < matrix.length; i++) {
            //while (flag)
            switch (state) {
                case 0:
                    traverseRowForward(matrix, row, col, rowOffset, list);
                    state = 1;
                    col = matrix.length - rowOffset;
                    row = row + 1;
                    rowOffset++;
                    break;
                case 1:
                    traverseColumnDownard(matrix, row, col, rowOffset, list);
                    state = 2;
                    row = matrix.length - rowOffset;
                    col = matrix.length - rowOffset;
                    break;
                case 2:
                    col = traverseRowBackward(matrix, row, col, rowOffset, list);
                    row = matrix.length - rowOffset;
                    state = 3;
                    break;
                case 3:
                    //traverseColumnUpward();
                    state = 0;
                    break;
            }
        }
        return null;
    }

    private int traverseRowBackward(int[][] matrix, int row, int col, int rowOffset, List<Integer> list) {
        int i;
        for (i = col; i > rowOffset; i--) {
            list.add(matrix[row][i]);
        }
        return i;
    }

    private void traverseColumnDownard(int[][] matrix, int row, int col, int rowOffset, List<Integer> list) {
        for (int i = row; i < matrix[0].length - rowOffset; i++) {
            list.add(matrix[i][col]);
        }
    }

    private void traverseRowForward(int[][] matrix, int curRow, int curCol, int rowOffset, List<Integer> list) {
        for (int i = curCol; i < matrix.length - rowOffset; i++) {
            list.add(matrix[curRow][i]);
        }
    }

}