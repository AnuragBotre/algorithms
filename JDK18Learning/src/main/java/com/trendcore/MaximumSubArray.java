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
    }

    private void testCase(int[] nums) {
        System.out.println(maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {

        //idea
        //use 2 pointer approach
        //first pointer will start from 0
        //second pointer will start from length
        //if sum is getting down then remove element
        //analyze each element

        List<Integer> sums = new ArrayList<>();

        int endPointer;
        int frontPointer;

        int maxSum = 0;
        boolean maxSumInitialized = false;

        for (int i = 0; i < nums.length; i++) {
            endPointer = i;
            int sum = nums[endPointer];
            int j;
            for (j = endPointer + 1; j < nums.length; j++) {
                if (sum > nums[j]) {
                    break;
                }
                sum = sum + nums[j];
            }
            i = j;
            if (!maxSumInitialized) {
                maxSum = sum;
                maxSumInitialized = true;
            } else {
                if (maxSum < sum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }
}
