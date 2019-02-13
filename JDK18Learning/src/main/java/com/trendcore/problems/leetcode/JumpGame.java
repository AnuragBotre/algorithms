package com.trendcore.problems.leetcode;

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
        j.testCase(new int[]{0});
        j.testCase(new int[]{1, 2});
        j.testCase(new int[]{2, 3, 1, 1, 4});
        j.testCase(new int[]{3, 2, 1, 0, 4});
        j.testCase(new int[]{3, 4, 1, 1, 1, 1, 0, 4});
    }

    private void testCase(int[] nums) {
        System.out.println(canJump(nums));
    }

    public boolean canJump(int[] nums) {

        int intermediateStages[] = new int[nums.length + 1];

        /*for (int i = 0; i < intermediateStages.length; i++) {
            intermediateStages[i] = Integer.MAX_VALUE;
        }*/

        for (int currentStep = 0; currentStep < nums.length - 1; currentStep++) {
            //can we jump from the current step and how many jump allowed
            int allowedJumps = nums[currentStep];

            if (intermediateStages[currentStep] != Integer.MAX_VALUE && intermediateStages[currentStep] + allowedJumps + 1 >= nums.length) {
                return true;
            }

            for (int j = currentStep + 1; j <= currentStep + allowedJumps; j++) {
                if(nums[j] != 0) {
                    intermediateStages[j] = currentStep + nums[j];
                }else{
                    intermediateStages[j] = Integer.MAX_VALUE;
                }
            }

        }

        return false;
    }

}
