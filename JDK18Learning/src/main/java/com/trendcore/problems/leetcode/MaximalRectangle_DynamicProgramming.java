package com.trendcore.problems.leetcode;

import java.util.Arrays;

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
public class MaximalRectangle_DynamicProgramming {

    class Temp {
        int height;
        int width;
        int area;
    }

    public int maximalRectangle(char[][] matrix) {

        Temp output[][] = new Temp[matrix.length][matrix[0].length];

        int maxArea = 0;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] != '0') {

                    if (i == 0 && j == 0) {
                        output[i][j] = new Temp();
                        output[i][j].width = 1;
                        output[i][j].height = 1;
                        maxArea = 1;
                    } else {
                        output[i][j] = new Temp();
                        if (j != 0) {
                            if (output[i][j - 1] != null) {
                                output[i][j].width = output[i][j - 1].width + 1;
                            } else {
                                output[i][j].width = 1;
                            }
                        } else {
                            output[i][j].width = 1;
                        }

                        if (i != 0) {
                            if (output[i - 1][j] != null) {
                                output[i][j].height = output[i - 1][j].height + 1;
                            } else {
                                output[i][j].height = 1;
                            }
                        } else {
                            output[i][j].height = 1;
                        }


                        if (i != 0) {
                            int width;
                            if (output[i - 1][j] != null) {
                                width = output[i - 1][j].width;
                            } else {
                                width = 1;
                            }
                            if (output[i][j].width >= width) {
                                int calculatedArea = width * output[i][j].height;
                                if (calculatedArea > output[i][j].width) {
                                    if (calculatedArea > output[i][j].height) {
                                        output[i][j].area = calculatedArea;
                                    } else {
                                        output[i][j].area = output[i][j].height;
                                    }
                                } else {
                                    if (output[i][j].width > output[i][j].height) {
                                        output[i][j].area = output[i][j].width;
                                    } else {
                                        output[i][j].area = output[i][j].height;
                                    }
                                }
                            }
                        } else {
                            output[i][j].area = output[i][j].width;
                        }

                        if (maxArea < output[i][j].area) {
                            maxArea = output[i][j].area;
                        }

                    }
                }
            }
        }

        Arrays.stream(output).forEach(ints -> {
                    Arrays.stream(ints).forEach(value -> {
                        if (value != null) {
                            System.out.print("{" + value.width + "," + value.height + "," + value.area + "}\t");
                        } else {
                            System.out.print("{" + 0 + "," + 0 + "," + 0 + "}\t");
                        }
                    });
                    System.out.println();
                }
        );

        //return maxArea;
        return maxArea;
    }

}
