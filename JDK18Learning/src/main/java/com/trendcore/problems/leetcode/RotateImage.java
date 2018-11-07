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
                System.out.print("\t" + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void rotate(int[][] matrix) {


        //Idea 2 rotation
        //visit each and every position
        //traverse diagonally


        int cnt = matrix.length - 1;
        int prev = 0;

        //traverse(0, 0, 0, 0, matrix, matrix[0][0], cnt,true);
        //traverse(0, 1, 0, 1, matrix, matrix[0][1], cnt, true, 0);
        traverse(0, 2, 0, 2, matrix, matrix[0][2], cnt, true, 0);
        //traverse(1, 1, 1, 1, matrix, matrix[1][1], cnt-1,true);
        /*for (int i = 0; i < matrix.length; i++, cnt--) {

            traverse(i, i, i, i, matrix, matrix[i][i], cnt,true);

            *//*for (int j = i; j < cnt; j++) {
                prev = matrix[i][j];
                int obj[] = getRowCol(i, j, matrix.length - 1);
                matrix[i][j] = matrix[obj[0]][obj[1]];
            }*//*
        }*/
        System.out.println();
    }

    private void traverse(int row, int col, int origRow, int origCol, int[][] matrix, int prev, int length, boolean firstTime, int i) {

        int copyTo[] = getRowCol(row, col, length, origRow, i);

        int copyFrom[] = getCopyFromRowCol(row, col, length, origRow);

        if (copyTo[0] == origRow && copyTo[1] == origCol) {
            matrix[row][col] = prev;
            return;
        }

        if (firstTime) {
            prev = matrix[row][col];
            matrix[row][col] = matrix[copyFrom[0]][copyFrom[1]];
        } else {
            int temp = matrix[row][col];
            matrix[row][col] = prev;
            prev = temp;
        }
        debug(matrix);
        traverse(copyTo[0], copyTo[1], origRow, origCol, matrix, prev, length, false, i+1);
    }

    private int[] getCopyFromRowCol(int row, int col, int length, int startPos) {
        int obj[] = new int[2];
        if (row == startPos && col == startPos) {
            obj[0] = length;
            obj[1] = startPos;
        } else if (row == startPos && col == length) {
            obj[0] = startPos;
            obj[1] = startPos;
        } else if (row == length && col == length) {
            obj[0] = startPos;
            obj[1] = length;
        } else if (row == length && col == startPos) {
            obj[0] = length;
            obj[1] = length;
        }

        if (row == 0) {
            obj[0] = length - col;
            obj[1] = startPos;
        } else {

        }

        return obj;
    }

    private int[] getRowCol(int row, int col, int length, int startPos, int i) {

        int obj[] = new int[2];
        if (row == startPos && col == startPos) {
            obj[0] = startPos;
            obj[1] = length;
        } else if (row == startPos && col == length) {
            obj[0] = length;
            obj[1] = length;
        } else if (row == length && col == length) {
            obj[0] = length;
            obj[1] = startPos;
        } else if (row == length && col == startPos) {
            obj[0] = startPos;
            obj[1] = startPos;
        }

        switch (i){
            case 0:
                obj[0] = col;
                obj[1] = length;
                break;
            case 1:
                obj[0] = length;
                obj[1] = length - row;
                break;
            case 2:
                obj[0] = col;
                obj[1] = startPos;
                break;
            case 3:
                obj[0] = startPos;
                obj[1] = length - row;
                break;
        }

        /*if (row == startPos) {
            obj[0] = col;
            obj[1] = length;
        } else if (col == length) {
            obj[0] = length;
            obj[1] = length - row;
        } else if (row == length) {

        } else if (col == startPos) {

        }*/

        return obj;
    }

    private void debug(int[][] matrix) {
        Scanner s = new Scanner(System.in);
        String next = s.next();
        System.out.println();
        printMatrix(matrix);
    }

    private void getLocation(int row, int col, int length, int startPos) {
        if (row == startPos && col == startPos) {

        } else if (row == startPos && col == length) {

        } else if (row == length && col == length) {

        } else if (row == length && col == startPos) {

        }
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
