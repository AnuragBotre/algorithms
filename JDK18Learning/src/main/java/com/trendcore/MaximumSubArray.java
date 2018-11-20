package com.trendcore;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * <p>
 * 53. Maximum Subarray
 * <p>
 * Given an integer array nums, find the contiguous subarray
 * (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution,
 * try coding another solution using the divide and conquer approach,
 * which is more subtle.
 */
public class MaximumSubArray {

    public static void main(String[] args) {
        MaximumSubArray m = new MaximumSubArray();
        m.testCase(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        m.testCase(new int[]{-1, -2, -3});
        m.testCase(new int[]{-3, -2, -1});
        m.testCase(new int[]{-3, -2, -1, 0});
    }

    private void testCase(int[] nums) {
        System.out.println(maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {

        int maxSum = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sum = nums[i];
                maxSum = nums[i];
            } else {
                int r = sum + nums[i];
                if (r < nums[i]) {
                    sum = nums[i];
                } else {
                    sum = r;
                }
            }

            if (maxSum < sum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }
}
