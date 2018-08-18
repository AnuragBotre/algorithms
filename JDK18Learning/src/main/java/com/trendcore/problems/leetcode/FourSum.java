package com.trendcore.problems.leetcode;

import java.util.List;

/**
 * https://leetcode.com/problems/4sum/description/
 * Given an array nums of n integers and an integer target,
 * are there elements a, b, c, and d in nums
 * such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.

 Note:

 The solution set must not contain duplicate quadruplets.

 Example:

 Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

 A solution set is:
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]
 */
public class FourSum {

    public static void main(String[] args) {
        FourSum f = new FourSum();
        f.fourSum(new int[]{1, 0, -1, 0, -2, 2},0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        //Approach 1 Brute Force 4 for loops
        //Approach 2 Modified Approach 1 3 for loops and store result in the hashmap

        //Approach 3 bifurcate array between positive array and negative array
        //then sort both array
        //start with negative array and try to find integers in the positive array

        //Approach 4 sort array go with each no and try to find
        //remaing 3 look ThreeSumClosest approach that needs to be implemented.
        //Or complete that example with this approach

        return null;
    }

}
