package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * 81. Search in Rotated Sorted Array II
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return true, otherwise return false.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * Follow up:
 * <p>
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */
public class SearchInRotatedArrayII {

    public boolean search(int[] nums, int target) {
        int high = nums.length - 1;
        //2,5,6,0,0,1,2

        if (nums.length == 0)
            return false;

        int pivotPosition = findPivot(nums);

        if (pivotPosition == 0) {
            //binary search
            return binarySearch(pivotPosition, nums.length - 1, nums, target);
        } else if (nums[pivotPosition] <= target && nums[high] >= target) {
            //2nd half
            return binarySearch(pivotPosition, nums.length - 1, nums, target);
        } else {
            //1st half
            return binarySearch(0, pivotPosition - 1, nums, target);
        }

    }

    private boolean binarySearch(int low, int high, int[] nums, int target) {

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return true;
            } else {
                if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return false;
    }

    private int findPivot(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return i;
            }
        }

        return 0;
    }

}
