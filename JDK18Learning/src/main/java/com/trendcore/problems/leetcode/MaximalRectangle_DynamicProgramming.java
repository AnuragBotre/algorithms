package com.trendcore.problems.leetcode;

import java.util.Arrays;
import java.util.Stack;

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


    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) {
            return 0;
        }


        int arr[] = new int[matrix[0].length];

        int maxArea = 0;

        for (int i = 0; i < matrix.length; i++) {
            //copy row into arr
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    arr[j] = 0;
                } else {
                    arr[j] = arr[j] + getInt(matrix[i][j]);
                }
            }

            int area = findLargestHistogram(arr);
            if (maxArea < area) {
                maxArea = area;
            }
        }

        //return maxArea;
        return maxArea;
    }

    public int findLargestHistogram(int[] arr) {

        /*int stack[] = new int[arr.length];
        int stackPointer = 0;*/
        Stack<Integer> stack = new Stack();

        int maxArea = 0;

        int i = 0;
        while (i < arr.length) {
            if (stack.isEmpty()) {
                //stack is empty

                stack.push(i);

            } else if (arr[stack.peek()] <= arr[i]) {
                stack.push(i);
            } else {
                //need to calculate area
                //1,2,3,4,3,2,1
                //1,2,3,4,

                int pos = 0;
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                    pos = stack.pop();
                    int element = arr[pos];
                    int area;
                    if (stack.isEmpty()) {
                        area = element * i;
                    } else {
                        //i-1 will be current position of highest element
                        //stack.peek() will give length elements to be processed
                        area = element * (i-1- stack.peek());
                    }

                    if (maxArea < area) {
                        maxArea = area;
                    }
                }
                stack.push(i);
            }
            i++;
        }

        int pos = 0;
        while (!stack.isEmpty()) {
            pos = stack.pop();
            int element = arr[pos];
            int area;
            if (stack.isEmpty()) {
                area = element * i;
            } else {
                area = element * (i-stack.peek()-1);
            }

            if (maxArea < area) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    private int getInt(char c) {
        return c == '0' ? 0 : 1;
    }


}
