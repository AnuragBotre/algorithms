package com.trendcore.problems.leetcode;

import java.util.List;

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

    class Coordinate {
        int x;
        int y;
    }

    public int maximalRectangle(char[][] matrix) {

        int maxArea = 0;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] != '0') {
                    int height = getHeight(i, j, matrix);
                    int[] widthAndHeight = getWidth(i, j, matrix, height);

                    int width = widthAndHeight[0];
                    int tempHeight = widthAndHeight[1];

                    int width1 = 1;
                    if (height == tempHeight) {
                        width1 = width;
                    }

                    int area1 = width1 * height;
                    int area2 = width * tempHeight;

                    int area = Math.max(area1, area2);

                    if (maxArea < area) {
                        maxArea = area;
                    }
                }
            }
        }

        return maxArea;
    }

    private int[] getWidth(int i, int j, char[][] matrix, int height) {
        int width = 1;
        int newHeight = 1;

        int finalHeight = height + j;

        if (finalHeight > matrix[0].length) {
            finalHeight = matrix[0].length;
        }

        for (int l = j; l < finalHeight; l++) {
            boolean zeroPresent = false;
            for (int k = i; k < matrix.length; k++) {

                if (matrix[k][l] != '0') {
                    //0 present in the row then this cannot be included
                    width++;
                } else {
                    zeroPresent = true;
                    break;
                }
            }
            if (!zeroPresent) {
                newHeight++;

            } else {
                break;
            }
        }
        return new int[]{width, newHeight};
    }

    private int getHeight(int i, int j, char[][] matrix) {
        int height = 0;

        for (int k = i; k < matrix.length; k++) {
            if (matrix[k][j] != '0') {
                height++;
            } else {
                break;
            }
        }
        return height;
    }

}
