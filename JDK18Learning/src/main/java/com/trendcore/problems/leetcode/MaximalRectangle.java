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

    class Coordinate {
        int x;
        int y;
    }

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

    class Tuple2 {
        int height;
        int width;

        public Tuple2(int width, int height) {
            this.height = height;
            this.width = width;
        }
    }

    private Tuple2 traverseMatrix(int i, int j, int width, int height, char[][] matrix, int originalPosI, int originalPosJ) {
        traverseDownWords(i, j, width + 1, height + 1, matrix, originalPosI, originalPosJ, -1);
        return null;
    }

    private boolean traverseDownWords(int i, int j, int width, int height, char[][] matrix, int originalPosI, int originalPosJ, int previousHeight) {
        if (i >= matrix.length || j >= matrix[0].length || matrix[i][j] == '0') {
            return false;
        } else {
            /*if (previousHeight != -1 && height == previousHeight) {
                return true;
            }*/
            boolean preVHeightIsSameAsThisHeight = traverseDownWords(i + 1, j, width, height + 1, matrix, originalPosI, originalPosJ, previousHeight);
            if (previousHeight != -1 && height == previousHeight) {
                //width = width + 1;
                //System.out.println(" " + previousHeight + " " + width + " i = " + i + ", j = " + j);
                int area = height * width;
                if (maxArea < area) {
                    maxArea = area;
                }
            } else {

            }
            /*if (preVHeightIsSameAsThisHeight) {
                height = height + 1;
            }*/
            /*int area = height * width;
            if (maxArea < area) {
                maxArea = area;
            }*/
            return traverseRight(originalPosI, j, width, 0, matrix, originalPosI, originalPosJ, height);
        }
    }

    private boolean traverseRight(int i, int j, int width, int height, char[][] matrix, int originalPosI, int originalPosJ, int previousHeight) {
        if (j >= matrix[0].length || i >= matrix.length || matrix[i][j] == '0') {
            return false;
        } else {
            return traverseDownWords(i, j + 1, width + 1, height + 1, matrix, originalPosI, originalPosJ, previousHeight);
        }
    }
}
