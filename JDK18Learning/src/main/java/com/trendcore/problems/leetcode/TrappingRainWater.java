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

        int endPointer = 0;

        for (int i = 0; i < height.length - 1; i++) {
            startPointer = i;
            if (!starPointerInit) {
                //get start pointer
                startPointer = getStartPointer(height, i);
                if(startPointer != null) {
                    System.out.println(" Start pointer :- " + startPointer);
                }
            }
        }


        return 0;
    }

    private Integer getStartPointer(int[] height, int i) {
        for (int j = i + 1; j < height.length; j++) {
            if (height[i] > height[j]) {
                return i;
            } else if (height[i] == height[j]) {

            } else {
                return j;
            }
        }

        return null;
    }

}
