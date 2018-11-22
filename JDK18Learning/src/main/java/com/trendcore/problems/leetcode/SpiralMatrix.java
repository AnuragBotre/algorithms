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
                {7},
                {9},
                {6}
        });

        s.testCase(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        s.testCase(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        });

        s.testCase(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        });
    }

    private void testCase(int[][] matrix) {
        List<Integer> integers = spiralOrder(matrix);
        printResult(integers);
    }

    private void printResult(List<Integer> integers) {
        System.out.println();
        for (Integer i : integers) {
            System.out.print(" " + i);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        //state
        int state = 0;
        int rowOffset = 0;
        int colOffset = 0;
        int offset = 0;

        int col = 0;
        int row = 0;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < matrix.length; ) {

            //while (flag)
            switch (state) {
                case 0: {
                    state = 1;
                    col = traverseRowForward(matrix, row, col, offset, list);
                    rowOffset++;
                    row = row + 1;
                }
                break;
                case 1: {

                    row = traverseColumnDownard(matrix, row, col, offset, list);
                    col = col - 1;
                    colOffset++;
                    state = 2;
                }
                break;
                case 2: {

                    col = traverseRowBackward(matrix, row, col, offset, list);
                    state = 3;
                    row = row - 1;
                }
                break;
                case 3:

                    offset++;
                    row = traverseColumnUpward(matrix, row, col, offset, list);
                    col = col + 1;
                    state = 0;
                    i++;

                    break;

            }

            if (completeTraversal(matrix, row, col, offset)) {
                break;
            }
        }
        return list;
    }

    private boolean completeTraversal(int[][] matrix, int row, int col, int offset) {
        if (row >= matrix.length - offset) {
            return true;
        }
        return false;
    }

    private int traverseColumnUpward(int[][] matrix, int row, int col, int rowOffset, List<Integer> list) {
        int i;
        for (i = row; i >= rowOffset; i--) {
            list.add(matrix[i][col]);
        }
        return i + 1;
    }

    private int traverseRowBackward(int[][] matrix, int row, int col, int rowOffset, List<Integer> list) {
        int i;
        for (i = col; i >= rowOffset; i--) {
            list.add(matrix[row][i]);
        }
        return i + 1;
    }

    private int traverseColumnDownard(int[][] matrix, int row, int col, int rowOffset, List<Integer> list) {
        int i;
        for (i = row; i < matrix.length - rowOffset; i++) {
            list.add(matrix[i][col]);
        }
        return i - 1;
    }

    private int traverseRowForward(int[][] matrix, int curRow, int curCol, int rowOffset, List<Integer> list) {
        int i;
        for (i = curCol; i < matrix[curRow].length - rowOffset; i++) {
            list.add(matrix[curRow][i]);
        }
        return i - 1;
    }

}