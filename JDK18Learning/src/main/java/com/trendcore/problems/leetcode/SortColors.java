package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/sort-colors/
 * <p>
 * 75. Sort Colors
 * <p>
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * <p>
 * Note: You are not suppose to use the library's sort function for this problem.
 * <p>
 * Example:
 * <p>
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 * <p>
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's,
 * then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors {

    public void sortColors(int[] nums) {

        /*int startPointerToZero = 0;
        int endPointerToZero = 0;

        int startPointerToOne = 0;
        int endPointerToOne = 0;

        int startPointerToTwo = 0;
        int endPointerToTwo = 0;*/

        approach1(nums);
    }

    public void approach1(int[] nums) {
        int counter[] = new int[3];

        for (int i = 0; i < nums.length; i++) {
            counter[nums[i]]++;
        }

        int numCounter = 0;
        for (int i = 0; i < counter.length; i++) {
            int c = counter[i];
            for (int j = 0; j < c; j++) {
                nums[numCounter] = i;
                numCounter++;
            }
        }
    }
}
