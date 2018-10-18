package com.trendcore.problems.leetcode;

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
        f.testCase(new int[]{1, 2, 0});
    }

    private void testCase(int[] ints) {
        firstMissingPositive(ints);
    }

    public int firstMissingPositive(int[] nums) {
        return 0;
    }

}
