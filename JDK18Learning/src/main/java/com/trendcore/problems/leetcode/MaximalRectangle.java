package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 * <p>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example:
 * Input:
 * <p>
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public class MaximalRectangle {

    private int maxArea = 0;

    public int maximalRectangle(char[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] != '0') {
                    traverseMatrix(i, j, 0, 0, matrix, i, j);
                }
            }
        }

        return maxArea;
    }


    private void traverseMatrix(int i, int j, int width, int height, char[][] matrix, int originalPosI, int originalPosJ) {
        traverseDownWords(i, j, width + 1, height + 1, matrix, originalPosI, originalPosJ, -1);
    }

    private void traverseDownWords(int i, int j, int width, int height, char[][] matrix, int originalPosI, int originalPosJ, int previousHeight) {
        if (i >= matrix.length || j >= matrix[0].length || matrix[i][j] == '0') {
            return;
        } else {
            if (previousHeight != -1 && height == previousHeight) {

                int area = height * width;
                if (maxArea < area) {
                    maxArea = area;
                }

                traverseRight(originalPosI, j, width, 0, matrix, originalPosI, originalPosJ, height);

            } else {

                traverseDownWords(i + 1, j, width, height + 1, matrix, originalPosI, originalPosJ, previousHeight);

                int area = height * width;
                if (maxArea < area) {
                    maxArea = area;
                }
                traverseRight(originalPosI, j, width, 0, matrix, originalPosI, originalPosJ, height);
            }
        }
    }

    private void traverseRight(int i, int j, int width, int height, char[][] matrix, int originalPosI, int originalPosJ, int previousHeight) {
        if (j >= matrix[0].length || i >= matrix.length || matrix[i][j] == '0') {
            return;
        } else {
            traverseDownWords(i, j + 1, width + 1, height + 1, matrix, originalPosI, originalPosJ, previousHeight);
        }
    }
}
