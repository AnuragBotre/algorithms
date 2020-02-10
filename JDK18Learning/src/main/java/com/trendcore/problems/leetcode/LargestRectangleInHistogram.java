package com.trendcore.problems.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * <p>
 * 84. Largest Rectangle in Histogram
 * <p>
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * <p>
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        return oneOfLeetCodeApproach(heights);
    }


    private int approach1(int[] heights) {
        /**
         * 2 pointers left and right
         * then at given time increment left pointer first then right
         *
         * calculate maximum area between 2 pointers.
         */

        int maxArea = 0;

        Map<Integer, Integer> cache = new HashMap();

        for (int i = 0; i < heights.length; i++) {
            for (int j = heights.length - 1; j >= 0 && j >= i; j--) {

                int minHeight = getMinHeight(i, j, heights);
                int width = (j + 1) - i;

                int area = minHeight * width;

                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }


        return maxArea;
    }

    private int getMinHeight(int leftPointer, int rightPointer, int[] heights) {

        int minHeight = 0;

        for (int i = leftPointer; i <= rightPointer; i++) {
            if (i == leftPointer) {
                minHeight = heights[i];
            } else {
                if (minHeight > heights[i]) {
                    minHeight = heights[i];
                }
            }
        }

        return minHeight;
    }

    /**
     * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/496471/Easy-solution-O(n)-for-beginner-with-explanation-JavaJS
     *
     * @param heights
     * @return
     */
    public int oneOfLeetCodeApproach(int[] heights) {

        if (heights == null || heights.length == 0)
            return 0;

        Stack<Integer> pStack = new Stack<>(); // stack of potential max area rectangle start index
        Stack<Integer> hStack = new Stack<>(); // stack of potential max area rectangle height
        int res = 0, tempPos = 0, tempHeight = 0;

        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            if (hStack.isEmpty() || h > hStack.peek()) {
                pStack.push(i);
                hStack.push(h);
            } else if (h < hStack.peek()) {
                while (!hStack.isEmpty() && h < hStack.peek()) {
                    tempPos = pStack.pop();
                    tempHeight = hStack.pop();
                    int area = tempHeight * (i - tempPos);
                    res = Math.max(res, area);
                }
                pStack.push(tempPos);
                hStack.push(h);
            }
        }

        // after last index of the array, calculate the rest
        while (!hStack.isEmpty()) {
            tempPos = pStack.pop();
            tempHeight = hStack.pop();
            int area = tempHeight * (heights.length - tempPos);
            res = Math.max(res, area);
        }

        return res;
    }

}
