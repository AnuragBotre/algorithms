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
                {7, 9, 6}
        });

        s.testCase(new int[][]{
                {2,5,8},
                {4,0,-1}
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
        int rowCount = 0;
        int colCount = 0;

        boolean initilzed = false;

        int offset = 0;

        int col = 0;
        int row = 0;


        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {

            if (!initilzed) {
                rowCount = matrix.length;
                colCount = matrix[0].length;
                initilzed = true;
            }


            row = i;
            col = i;
            offset = i;


            col = traverseRowForward(matrix, row, col, offset, list, rowCount, colCount);
            rowCount--;
            row = row + 1;

            row = traverseColumnDownard(matrix, row, col, offset, list, rowCount, colCount);
            colCount--;
            col = col - 1;

            col = traverseRowBackward(matrix, row, col, offset, list, rowCount, colCount);
            rowCount--;
            row = row - 1;

            row = traverseColumnUpward(matrix, row, col, offset, list, rowCount, colCount);
            colCount--;
        }
        return list;
    }

    private boolean completeTraversal(int[][] matrix, int row, int col, int offset, int prevRow, int prevCol) {
        /*if (row >= matrix.length - offset || col >= matrix.length || col < 0 || row < 0) {
            return true;
        }
        return false;*/

        if ((row == prevRow && prevCol == col) || col < 0 || row < 0) {
            return true;
        }

        return false;
    }

    private int traverseColumnUpward(int[][] matrix, int row, int col, int rowOffset, List<Integer> list, int rowCount, int colCount) {
        int i = row;
        int cnt;
        if (colCount > 0) {
            for (cnt = 0; i > rowOffset && cnt < rowCount; i--, cnt++) {
                list.add(matrix[i][col]);
            }
        }
        return i + 1;
    }

    private int traverseRowBackward(int[][] matrix, int row, int col, int rowOffset, List<Integer> list, int rowCount, int colCount) {
        int i = col;
        int cnt;
        if (rowCount > 0) {
            for (cnt = 0; i >= rowOffset && cnt < colCount; i--, cnt++) {
                list.add(matrix[row][i]);
            }
        }
        return i + 1;
    }

    private int traverseColumnDownard(int[][] matrix, int row, int col, int rowOffset, List<Integer> list, int rowCount, int colCount) {
        int i = row;
        int cnt;
        if (colCount > 0) {
            for (i = row, cnt = 0; i < matrix.length - rowOffset && cnt < rowCount; i++, cnt++) {
                list.add(matrix[i][col]);
            }
        }
        return i - 1;
    }

    private int traverseRowForward(int[][] matrix, int curRow, int curCol, int rowOffset, List<Integer> list, int rowCount, int colCount) {
        int i = curCol;
        int cnt;
        if (rowCount > 0) {
            for (cnt = 0; i < matrix[curRow].length - rowOffset && cnt < colCount; i++, cnt++) {
                list.add(matrix[curRow][i]);
            }
        }
        return i - 1;
    }

}