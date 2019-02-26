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



        int rowCount = n;
        int colCount = n;
        int output[][] = new int[n][n];

        int rowStart;
        int colStart;

        for (int i = 0; i < n / 2; i++) {

            /*for (int j = 0; j < n; j++, cnt++) {

                System.out.print(cnt + " ");
            }*/
            //print row wise
            rowStart = i;
            colStart = i;


            int i1 = printRowWise(output,i, rowCount, i);
            rowCount--;
            int i2 = printColumnWise(output,rowStart+1, rowCount, i1);
            colCount--;
            int i3 = printRowWiseReverse(output, i2, rowCount, i2);
            rowCount--;
            printColumnWiseReverse(output,i3, rowCount, colCount);
            colCount--;

            /*if(rowCount == colCount){
                break;
            }*/

            //print column wise

            //print row wise

            //print column wise

        }
        return output;
    }

    private int printColumnWiseReverse(int[][] output, int start, int end, int col) {
        int i;
        int c;
        for (i = start,c = 0; c < end; i--,c++) {
            output[i][col] = cnt;
            cnt++;
        }
        return i+1;
    }

    private int printRowWiseReverse(int[][] output, int start, int end, int row) {
        int i;
        int c;
        for (i = start,c = 0; c < end; i--,c++) {
            output[row][i] = cnt;
            cnt++;
        }
        return i+1;
    }

    private int printColumnWise(int[][] output, int start, int end, int col) {
        int i;
        for (i = start; i < end; i++) {
            output[i][col] = cnt;
            cnt++;
        }
        return i-1;
    }

    private int printRowWise(int[][] output, int start, int end, int row) {
        int i;
        for (i = start; i < end; i++) {
            output[row][i] = cnt;
            cnt++;
        }
        return i-1;
    }

}
