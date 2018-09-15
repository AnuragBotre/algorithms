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
        testCase(new int[]{1}, 1);
        testCase(new int[]{1, 3}, 0);
        testCase(new int[]{1, 3}, 1);
        testCase(new int[]{1, 3}, 3);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 8);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 9);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 1);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 10);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 2);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 3);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 4);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 6);
        testCase(new int[]{1, 2, 3, 4, 6, 8, 9, 10}, 5);
        testCase(new int[]{3, 1}, 4);
        testCase(new int[]{3, 1}, 3);
        testCase(new int[]{3, 1}, 1);
        testCase(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8);
        testCase(new int[]{5,1,2,3,4}, 1);

        testCaseForPivot(new int[]{1, 2, 3});
        testCaseForPivot(new int[]{4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2});
        testCaseForPivot(new int[]{4, 5, 6, 7, 0, 1, 2});
        testCaseForPivot(new int[]{1});
        testCaseForPivot(new int[]{5,1,2,3,4});
    }

    private static void testCaseForPivot(int[] ints) {
        SearchInRotatedArray s = new SearchInRotatedArray();
        int pivot = s.findPivot(ints);
        System.out.println(pivot + " " + ints[pivot]);
    }

    private static void testCase(int[] ints, int i) {
        SearchInRotatedArray s = new SearchInRotatedArray();
        System.out.println(s.search(ints, i));
    }


    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        //First Find the pivot
        int pivot = findPivot(nums);

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;

        if (!(nums[low] < nums[high])) {
            if (nums[pivot] <= target && target <= nums[high]) {
                low = pivot;
            } else {
                high = pivot - 1;
            }
        }

        //then apply binary search
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private int findPivot(int[] nums) {


        //4,5,6,7,8,9,10,11,0,1,2
        //5,1,2,3,4

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;

        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid + 1] < nums[mid]) {
                return mid + 1;
            } else if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid;
            }
        }


        return mid;
    }

}
