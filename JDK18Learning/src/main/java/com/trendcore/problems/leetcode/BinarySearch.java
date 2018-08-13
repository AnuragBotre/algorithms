package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/binary-search/description/
 * 704. Binary Search
 * <p>
 * Given a sorted (in ascending order) integer array nums of n elements and a target value,
 * write a function to search target in nums.
 * If target exists, then return its index, otherwise return -1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch b = new BinarySearch();
        System.out.println(b.search(new int[]{-1,0,3,5,9,12},9));
        System.out.println(b.search(new int[]{-1,0,3,5,9,12},2));
        System.out.println(b.search(new int[]{},2));
    }

    public int search(int[] nums, int target) {
        if(nums.length == 0){
            return -1;
        }

        int low = 0;
        int high = nums.length-1;
        int mid = 0;

        while(low <= high){
            mid = (low + high) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }

        return -1;
    }

}
