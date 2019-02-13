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

        List<Integer> list = new ArrayList();

        /*for (int i = 0; i < intermediateStages.length; i++) {
            intermediateStages[i] = Integer.MAX_VALUE;
        }*/
        int currentStep = 0;
        for (; currentStep < nums.length - 1; ) {
            //can we jump from the current step and how many jump allowed
            int allowedJumps = nums[currentStep];

            if (allowedJumps + (currentStep + 1) >= nums.length) {
                return true;
            } else if (allowedJumps == 0) {
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

}
