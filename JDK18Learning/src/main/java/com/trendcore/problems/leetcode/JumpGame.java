package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/jump-game/
 * 55. Jump Game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {

    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        j.testCase(new int[]{0}, true);
        j.testCase(new int[]{1, 2}, true);
        j.testCase(new int[]{2, 0}, true);
        j.testCase(new int[]{0, 2, 3}, false);
        j.testCase(new int[]{3, 0, 8, 2, 0, 0, 1}, true);
        j.testCase(new int[]{2, 3, 1, 0, 0, 0, 1, 4}, false);
        j.testCase(new int[]{2, 3, 1, 1, 4}, true);
        j.testCase(new int[]{3, 2, 1, 0, 4}, false);
        j.testCase(new int[]{3, 4, 1, 1, 1, 1, 0, 4}, false);
        j.testCase(new int[]{8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5}, true);
    }

    private void testCase(int[] nums, boolean expected) {
        Solution s = new Solution();
        System.out.println(s.canJump(nums) + " " + expected);
    }

    public boolean canJump(int[] nums) {

        int[] dp = new int[nums.length + 1];

        for (int i = nums.length - 2; i >= 0; --i) {
            int k = nums[i];
            if (k + i >= nums.length - 1) { /* At last element */
                dp[i] = 1;
                continue;
            }

            dp[i] = Integer.MAX_VALUE;

            for (; k > 0; --k) {
                if (dp[i + k] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i + k] + 1, dp[i]);
            }
        }
        return dp[0] != Integer.MAX_VALUE;
    }

    public boolean approach1(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        List<Integer> list = new ArrayList();
        int currentStep = 0;
        for (; currentStep < nums.length - 1; ) {
            //can we jump from the current step and how many jump allowed
            int allowedJumps = nums[currentStep];

            if (allowedJumps + (currentStep + 1) >= nums.length) {
                return true;
            } else if (allowedJumps == 0) {
                if (list.isEmpty()) {
                    return false;
                }
                currentStep = list.remove(0);
            }

            for (int j = currentStep + 1; j <= currentStep + allowedJumps; j++) {

                if (nums[j] != 0) {

                    if (list.isEmpty()) {
                        list.add(j);
                    } else {
                        Integer removedStep = list.remove(list.size() - 1);
                        int canJumpFromTheRemovedStep = (removedStep + 1) + nums[removedStep];

                        int canJumpFromTheJthStep = (j + 1) + nums[j];

                        if (canJumpFromTheRemovedStep > canJumpFromTheJthStep) {
                            list.add(removedStep);
                            list.add(j);
                        } else {
                            list.add(j);
                            list.add(removedStep);
                        }
                    }
                }
            }

            if (list.isEmpty()) {
                //Dest cannot be reached.
                return false;
            } else {
                currentStep = list.remove(0);
            }


        }

        return false;
    }


    /*
    Greedy solution. We maintain the variable "reach" to tell how far we can reach based on the total nums we've accumulated so far.
    At each iteration of nums[i], we add this to the "reach" and
    compare this value with the current reach. Take the max of the two as the new reach.
    The loop should end when i is reaching "reach" meaning we can't go futher.
    Or when i is reaching n where n = nums.length
    At the end we check if i == n then we know it reaches the end.
    */
    class Solution {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int i = 0;
            int reach = 0;
            int count = 0;
            while (i < n && i <= reach) {
                reach = Math.max(i + nums[i], reach);
                i++;
                count++;
            }

            System.out.println(" No of steps required. " + count);

            return i == n;
        }
    }

}
