package com.trendcore.problems.leetcode;

import java.util.Scanner;

/**
 * https://leetcode.com/problems/rotate-image/
 * <p>
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * <p>
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * Example 2:
 * <p>
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class RotateImage {

    /**
     * @param args
     */
    public static void main(String[] args) {
        RotateImage r = new RotateImage();
        /*r.testCase(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });*/

        r.testCase(new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        });
    }

    private void testCase(int[][] matrix) {
        rotate(matrix);

        printMatrix(matrix);
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void rotate(int[][] matrix) {


        //Idea 2 rotation
        //visit each and every position
        //traverse diagonally


        int cnt = matrix.length;
        int prev = 0;

        for (int i = 0; i < matrix.length; i++, cnt--) {

            traverse(i, i, i, i, matrix,matrix[i][i]);

            /*for (int j = i; j < cnt; j++) {
                prev = matrix[i][j];
                int obj[] = getRowCol(i, j, matrix.length - 1);
                matrix[i][j] = matrix[obj[0]][obj[1]];
            }*/
        }

    }

    private void traverse(int row, int col, int origRow, int origCol, int[][] matrix,int prev) {

        int obj[] = getRowCol(row, col, matrix.length - 1);

        if (obj[0] == origRow && obj[1] == origCol) {
            return;
        }

        prev = matrix[row][col];

        matrix[row][col] = matrix[obj[0]][obj[1]];
        traverse(obj[0], obj[1], origRow, origCol, matrix,prev);
    }

    private int[] getRowCol(int curRow, int curCol, int matrixLength) {

        int obj[] = new int[2];
        if (curRow == 0 && curCol == 0) {
            obj[0] = matrixLength;
            obj[1] = matrixLength;
        } else if (curRow == 0) {

        }


        return obj;
    }

    private void approach1(int[][] matrix) {
        for (int i = 0, row = 0; i < matrix.length; i++, row++) {
            int oThEleOfRow = matrix[row][0];
            for (int j = matrix.length - 1, col = 0; j >= 0; j--, col++) {

                /*if (row == matrix.length - 1 && col > 0)
                    break;*/

                if (col == matrix.length - 1) {
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[matrix.length - 1][row];
                    matrix[matrix.length - 1][row] = temp;
                } else {
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[j][i];
                    matrix[j][i] = temp;
                }

                //System.out.print(matrix[j][i]);
                debug(matrix, row, col);
            }
            System.out.println();
        }
    }

    private void debug(int[][] matrix, int row, int col) {
        Scanner s = new Scanner(System.in);
        String next = s.next();
        System.out.println();
        System.out.println(" ROw :- " + row + " Col :- " + col);
        printMatrix(matrix);
    }

}
