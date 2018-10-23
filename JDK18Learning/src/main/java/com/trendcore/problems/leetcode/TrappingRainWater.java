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
        System.out.println("Start Pointer :- " + startPointer + " End Pointer :- " + endPointer);
        return 0;
    }

    private Integer getEndPointer(int[] height, Integer startPointer) {
        for (int i = startPointer + 2; i < height.length; i++) {
            if (height[i] >= height[startPointer]) {
                return i;
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
