package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * <p>
 * 45. Jump Game II
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 * <p>
 * You can assume that you can always reach the last index.
 */
public class JumpGameII {

    public static void main(String[] args) {
        JumpGameII j = new JumpGameII();
        j.testCase(new int[]{2, 3, 1, 1, 4});
    }

    private void testCase(int[] nums) {
        System.out.println(jump(nums));
    }

    public int jump(int[] nums) {

        int dest = nums.length - 1;

        List<List<Integer>> combinationList = new ArrayList<>();
        //List list1 = new ArrayList();

        traverse(nums, 0, combinationList);

        return 0;
    }

    private void traverse(int[] nums, int position, List<List<Integer>> combinationList) {



        for (int i = position; i < nums[position]; i++) {
            List list = new ArrayList();
            list.add(nums[position]);
            traverseFurther(nums, i, position,list);
            combinationList.add(list);
        }

    }

    private void traverseFurther(int[] nums, int i, int position, List list) {

    }

}
