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

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i] + " ");
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {



        for (int i = 0; i < n; i++) {

            //print row wise

            //print column wise

            //print row wise

            //print column wise

            /*for (int j = 0; j < n; j++) {
                //
            }*/
        }
        return null;
    }

}
