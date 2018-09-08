package com.trendcore.problems.leetcode;

import java.util.Arrays;

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
 * 1,3,2 -> 2,1,3
 * 1,1,1,5 -> 1,1,5,1
 * 1,1,2,3 -> 1,1,3,2
 * 1,1,2,2 -> 1,2,1,2
 * 1,2,2,2 -> 2,1,1,1
 */
public class NextPermutation {

    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3});
        testCase(new int[]{3, 2, 1});
        testCase(new int[]{1, 1, 5});
        testCase(new int[]{1, 3, 2});
        testCase(new int[]{1, 1, 1, 5});
        testCase(new int[]{1, 1, 2, 3});
        testCase(new int[]{1, 1, 2, 2});
        testCase(new int[]{1, 2, 2, 2});
    }

    private static void testCase(int[] nums) {
        NextPermutation n = new NextPermutation();
        n.nextPermutation(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(" " + nums[i]);
        }
        System.out.println();
    }

    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        //solve using 2 pointer
        //compare i and i+1
        //if i+1 is greater than those can be swapped
        //in case of 1 1 2 2 min should point to 1 and max should point to 2

    }

}
