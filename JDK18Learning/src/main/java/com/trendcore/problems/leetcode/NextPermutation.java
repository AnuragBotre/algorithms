package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/next-permutation/description/
 * 31. Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically
 * next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange
 * it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column
 * and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public static void main(String[] args) {
        testCase(new int[]{1,2,3});
        testCase(new int[]{3,2,1});
        testCase(new int[]{1,1,5});
    }

    private static void testCase(int[] nums) {

    }

    public void nextPermutation(int[] nums) {
        for(int i = 0 ; i < nums.length ; i++){
            System.out.println(nums[i]);
        }
    }

}
