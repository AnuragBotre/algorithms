package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/spiral-matrix-ii/
 * 59. Spiral Matrix II
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {

    public static void main(String[] args) {
        SpiralMatrixII s = new SpiralMatrixII();
        s.testCase(3);
        s.cnt = 1;
        s.testCase(4);
    }

    private void testCase(int n) {
        int[][] ints = generateMatrix(n);

        printResult(ints);
    }

    private void printResult(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j] + " ");
            }
            System.out.println();
        }
    }

    int cnt = 1;

    public int[][] generateMatrix(int n) {

        int rowstart = 0;
        int colstart = 0;

        int output[][] = new int[n][n];

        int colEntries = n;
        int rowEntries = n;

        int rowNumToProcess = 0;
        int colNumToProcess = 0;

        for (int i = 0; i < n / 2 + 1; i++) {

            rowstart = i;
            colNumToProcess = printRowWise(output, rowstart, colEntries, rowNumToProcess);
            rowEntries--;

            rowNumToProcess = printColumnWise(output, rowstart + 1, rowEntries, colNumToProcess);
            colEntries--;

            colNumToProcess = printRowWiseReverse(output, colNumToProcess - 1, colEntries, rowNumToProcess);
            rowEntries--;

            rowNumToProcess = printColumnWiseReverse(output, rowNumToProcess - 1, rowEntries, colNumToProcess);
            colEntries--;

        }
        return output;
    }

    private int printColumnWiseReverse(int[][] output, int start, int end, int col) {
        int i;
        int c;
        for (i = start, c = 0; c < end; i--, c++) {
            output[i][col] = cnt;
            cnt++;
        }
        return i + 1;
    }

    private int printRowWiseReverse(int[][] output, int start, int end, int row) {
        int i;
        int c;
        for (i = start, c = 0; c < end; i--, c++) {
            output[row][i] = cnt;
            cnt++;
        }
        return i + 1;
    }

    private int printColumnWise(int[][] output, int start, int end, int col) {
        int i;
        int c;
        for (i = start,c = 0; c < end; i++,c++) {
            output[i][col] = cnt;
            cnt++;
        }
        return i - 1;
    }

    private int printRowWise(int[][] output, int start, int end, int row) {
        int i;
        int c;
        for (i = start,c = 0; c < end; i++,c++) {
            output[row][i] = cnt;
            cnt++;
        }
        return i - 1;
    }

}
