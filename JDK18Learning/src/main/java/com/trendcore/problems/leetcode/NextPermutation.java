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

        //find max element and its position
        int max = 0;
        int maxPosition = 0;

        int secondMax = 0;
        int secondMaxPosition = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                max = nums[i];
                maxPosition = i;

                secondMax = nums[i];
                secondMaxPosition = i;
            } else {
                if (max < nums[i]) {

                    secondMax = max;
                    secondMaxPosition = maxPosition;

                    max = nums[i];
                    maxPosition = i;
                }

            }
        }

        if (maxPosition == 0) {
            Arrays.sort(nums);
        } else {
            nums[maxPosition] = secondMax;
            nums[secondMaxPosition] = max;
        }

        //System.out.println("Max :- " + max + " Max Position :- " + maxPosition + " Second Max :- " + secondMax + " Second Max Positon :- " + secondMaxPosition);

        //find smaller number than max and swap two position
        //Also if Max number position is 0 then also return sorted array
        //if not found then return sorted nums

    }

}
