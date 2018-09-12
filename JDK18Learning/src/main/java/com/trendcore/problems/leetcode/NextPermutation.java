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
 * 2,3,1 -> 3,1,2
 * 1,1,1,5 -> 1,1,5,1
 * 1,1,2,3 -> 1,1,3,2
 * 1,1,2,2 -> 1,2,1,2
 * 1,2,2,2 -> 2,1,1,1
 */
public class NextPermutation {

    public static void main(String[] args) {
        testCase(new int[]{1, 2, 5, 4, 7, 6, 9, 5, 3, 1});
        testCase(new int[]{1, 2, 3});
        testCase(new int[]{3, 2, 1});
        testCase(new int[]{1, 1, 5});
        testCase(new int[]{1, 3, 2});
        testCase(new int[]{2, 3, 1});
        testCase(new int[]{1, 1, 1, 5});
        testCase(new int[]{1, 1, 2, 3});
        testCase(new int[]{1, 1, 2, 2});
        testCase(new int[]{1, 2, 2, 2});
        testCase(new int[]{2,3,1,3,3});
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

        //start with 0 try to find just max


        if (nums.length < 2) {
            return;
        }

        //swap last no
        //if swapped with max no then re-swapping is required

        //algo
        //First find no a[i-1] > a[i] in decreasing order.
        boolean found = false;
        int i;
        for (i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                found = true;
                break;
            }
        }

        if (found) {


            int justMaxPos = 0;
            boolean justMaxInitialized = false;
            for (int j = i - 1; j < nums.length; j++) {
                if (!justMaxInitialized) {
                    if (nums[j] > nums[i - 1]) {
                        justMaxPos = j;
                        justMaxInitialized = true;
                    }
                } else {
                    if (nums[j] <= nums[justMaxPos]) {
                        if (nums[j] > nums[i - 1]) {
                            justMaxPos = j;
                            justMaxInitialized = true;
                        }
                    }
                }
            }

            if (justMaxInitialized) {
                int temp = nums[justMaxPos];
                nums[justMaxPos] = nums[i - 1];
                nums[i - 1] = temp;

                for (int firstPointer = i, lastPointer = nums.length - 1; firstPointer < lastPointer; firstPointer++, lastPointer--) {
                    int t = nums[firstPointer];
                    nums[firstPointer] = nums[lastPointer];
                    nums[lastPointer] = t;
                }
            }
        } else {
            Arrays.sort(nums);
        }

    }

}
