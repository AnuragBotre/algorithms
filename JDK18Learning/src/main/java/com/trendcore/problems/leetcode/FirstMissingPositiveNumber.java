package com.trendcore.problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 * <p>
 * Input: [7,8,9,11,12]
 * Output: 1
 */
public class FirstMissingPositiveNumber {

    public static void main(String[] args) {
        FirstMissingPositiveNumber f = new FirstMissingPositiveNumber();
        /*f.testCase(new int[]{1, 2, 0});
        f.testCase(new int[]{3, 4, -1, 1});*/
        f.testCase(new int[]{100, 101, -1, 99, 1, 2, 3, 10, 6, 4});
    }

    private void testCase(int[] ints) {
        System.out.println(firstMissingPositive1(ints));
    }

    //Taken help from Leet code discussion
    public int firstMissingPositive(int[] nums) {

        int t[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 >= 0 && nums[i] - 1 < nums.length) {
                t[nums[i] - 1] = 1;
            }
        }

        int i = 0;
        for (i = 0; i < t.length; i++) {
            if (t[i] == 0) {
                return i + 1;
            }
        }

        return i + 1;
    }


    /**
     * Leet Code optmized solution.
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] <= len && nums[i] >= 1 && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
