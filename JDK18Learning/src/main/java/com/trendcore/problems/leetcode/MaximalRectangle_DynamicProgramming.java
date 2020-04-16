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

        class HeightWise {
            int area;
            int height;
            int width;
        }

        class WidthWise {
            int area;
            int height;
            int width;
        }


        HeightWise heightWise = new HeightWise();
        WidthWise widthWise = new WidthWise();

        int area;
    }

    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) {
            return 0;
        }

        Temp output[][] = new Temp[matrix.length][matrix[0].length];

        int maxArea = 0;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] != '0') {

                    if (i == 0 && j == 0) {
                        output[i][j] = new Temp();
                        output[i][j].heightWise.height = 1;
                        output[i][j].heightWise.width = 1;

                        output[i][j].widthWise.width = 1;
                        output[i][j].widthWise.height = 1;
                        maxArea = 1;
                    } else {
                        output[i][j] = new Temp();

                        //when j == 0 then increase height

                        /*
                            for given i,j
                                        */
                        if (i == 0) {

                            if (matrix[i][j - 1] == '0') {
                                //whatever height and width 1
                                output[i][j].heightWise.height = 1;
                                output[i][j].heightWise.width = 1;
                                output[i][j].heightWise.area = 1;

                                output[i][j].widthWise.height = 1;
                                output[i][j].widthWise.width = 1;
                                output[i][j].widthWise.area = 1;
                            } else {
                                output[i][j].heightWise.height = output[i-1][j].heightWise.height + 1;
                                output[i][j].heightWise.width = 1;
                                output[i][j].heightWise.area = output[i][j].heightWise.height * output[i][j].heightWise.width;

                                output[i][j].widthWise.height = 1;
                                output[i][j].widthWise.width = 1;
                                output[i][j].widthWise.area = 1;
                            }


                        } else if (j == 0) {
                            if (matrix[i - 1][j] == '0') {
                                //whatever width and height 1
                                output[i][j].widthWise.width = output[i][j - 1].widthWise.width + 1;
                                output[i][j].widthWise.height = 1;
                                int widthWiseArea = (output[i][j - 1].widthWise.width + 1) * 1;
                            }
                        } else {

                            if (matrix[i - 1][j] != '0' && matrix[i][j - 1] != '0') {
                                //area will be calculated height wise or width wise
                                int heightWiseArea = (output[i - 1][j].heightWise.height + 1) * output[i - 1][j].heightWise.width;
                                int widthWiseArea = (output[i][j - 1].widthWise.width + 1) * output[i][j - 1].widthWise.height;

                            } else if (matrix[i - 1][j] == '0') {
                                //whatever width and height 1
                                int widthWiseArea = (output[i][j - 1].widthWise.width + 1) * 1;
                            } else if (matrix[i][j - 1] == '0') {
                                //whatever height and width 1
                                int heightWiseArea = (output[i][j - 1].heightWise.height + 1) * 1;
                            }
                        }
                        /*    output[i-1].width + 1 * output[i-1].height
                         */

                    }
                }
            }
        }

        debug(output);

        //return maxArea;
        return maxArea;
    }

    private void debug(Temp[][] output) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        Arrays.stream(output).forEach(ints -> {
                    Arrays.stream(ints).forEach(value -> {
                        if (value != null) {
                            System.out.print("{" + value.widthWise.width + "," + value.widthWise.height + "," + value.area + "}\t\t\t");
                        } else {
                            System.out.print("{" + 0 + "," + 0 + "," + 0 + "}\t\t\t");
                        }
                    });
                    System.out.println();
                }
        );
    }

}
