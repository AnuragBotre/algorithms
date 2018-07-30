package com.trendcore.problems.leetcode;

/**
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/description/
 * <p>
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * <p>
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater c = new ContainerWithMostWater();
        int c1[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(c.maxArea(c1));

        //TODO : Need to optimize this solution.

    }

    public int maxArea(int[] height) {

        //May be sliding window may work

        int maxArea = 0;
        int startPointer = 0;
        int endPointer = height.length - 1;
        boolean flag = true;
        for (; startPointer < height.length; startPointer++) {
            int length = Math.min(height[startPointer], height[endPointer]);
            int width = endPointer - startPointer;
            int area = length * width;

            if (maxArea < area) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    private int bruteForceApproach(int[] height) {
        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                //calculate area for all the inouts
                int lenth = Math.min(height[i], height[j]);
                int width = j - i;
                int area = lenth * width;
                if (maxArea < area) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }

}
