package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 * 33. Search in Rotated Sorted Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array
 * return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchInRotatedArray {

    public static void main(String[] args) {
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 4);
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 1);
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 5);
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 6);
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 7);
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 1);
    }

    private static void testCase(int[] ints, int i) {
        SearchInRotatedArray s = new SearchInRotatedArray();
        System.out.println(s.search(ints, i));
    }


    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) / 2;

        boolean flag = true;

        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                //need to find the partition
                //need to check if given no is in the first half or second
                            }
        }

        return -1;
    }

}
