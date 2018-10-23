package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * Given n non-negative integers representing an elevation map where
 * the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * <p>
 * The above elevation map is represented by
 * array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * <p>
 * Thanks Marcos for contributing this image!
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        t.testCase(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        t.testCase(new int[]{1, 0, 1});
        t.testCase(new int[]{1, 1, 1});
        t.testCase(new int[]{1, 2, 1});
        t.testCase(new int[]{1, 2, 3});
        t.testCase(new int[]{2, 2, 3});
        t.testCase(new int[]{2, 0, 3});
        t.testCase(new int[]{4, 2, 3});
        t.testCase(new int[]{5, 4, 1, 2});
        t.testCase(new int[]{5,2,1,2,1,5});
        t.testCase(new int[]{4, 3, 2, 1, 0, 1, 2, 3, 4});
    }

    private void testCase(int[] height) {
        System.out.println(trap(height));
    }

    public int trap(int[] height) {

        Integer startPointer = 0;
        boolean starPointerInit = false;

        int area = 0;

        Integer endPointer = 0;

        for (int i = 0; i < height.length; i++) {
            //get start pointer
            startPointer = getStartPointer(height, i);

            if (startPointer != null) {
                //from start pointer + 2 get end pointer
                endPointer = getEndPointer(height, startPointer);

                if (endPointer != null) {
                    area = area + calculateArea(height, startPointer, endPointer);

                    //after all need to adjust i
                    i = endPointer - 1;
                }
            }

        }


        return area;
    }

    private int calculateArea(int[] height, Integer startPointer, Integer endPointer) {
        int area = 0;
        int b = Math.min(height[startPointer], height[endPointer]);
        for (int i = startPointer + 1; i < endPointer; i++) {
            int r = height[i] < b ? b - height[i] : 0;
            area = area + r;
        }
        return area;
    }

    private Integer getEndPointer(int[] height, Integer startPointer) {

        //TODO : need to calculate the end Pointer

        if (startPointer < height.length) {

            //need to find end pointer greater than start pointer heigh
            boolean needToFindEndPointerGreaterThanStartPointerHeight = false;

            int heightOfStartPointer = height[startPointer];
            int cnt = 0;
            int endPointer = startPointer + 1;

            boolean endPointerInit = false;

            for (int i = startPointer + 1; i < height.length; i++) {

                if (height[i] >= height[startPointer]) {
                    endPointer = i;
                    endPointerInit = true;
                } else if (endPointerInit && height[i] < endPointer) {
                    break;
                }
                startPointer = i;
                cnt++;
            }

            if (endPointerInit) {
                return endPointer;
            }
        }
        return null;
    }

    private Integer getStartPointer(int[] height, int currentPos) {
        int j;
        for (j = currentPos + 1; j < height.length; j++) {
            if (height[currentPos] > height[j]) {
                return currentPos;
            }
            currentPos = j;
        }

        return j;
    }

}
