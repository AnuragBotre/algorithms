package com.trendcore.problems.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/4sum/description/
 * Given an array nums of n integers and an integer target,
 * are there elements a, b, c, and d in nums
 * such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class FourSum {

    public static void main(String[] args) {
        FourSum f = new FourSum();
        f.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
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
        //Implemented this algo has problems for few inputs need to think

        return bruteForce(nums, target);
    }

    private List<List<Integer>> bruteForce(int[] nums, int target) {

        Map map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int result = 0 - (nums[i] + nums[j] + nums[k]);
                    if (map.get(result) != null) {

                    } else {
                        map.put(result, 0);
                    }
                }
            }
        }
        return null;
    }

    private List<List<Integer>> approach3(int[] nums, int target) {
        List<Integer> negativeNums = new ArrayList(nums.length);
        List<Integer> positiveNums = new ArrayList(nums.length);
        List<Integer> zerosNums = new ArrayList<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negativeNums.add(nums[i]);
            } else if (nums[i] == 0) {
                zerosNums.add(nums[i]);
            } else {
                positiveNums.add(nums[i]);
            }
        }

        Collections.sort(negativeNums);
        Collections.sort(positiveNums);

        //addition of 4 nums is 0
        //4,-4,0,0
        //4,-4,5,-5
        //4+4,-8,0
        //4+4+4,-12,0
        //4,4,-4,-4

        //-9,-7,-5,-2,0,0,0,1,2,5,9,10,12
        //-9,-7,-5,-2,0,0,0,1,2,5

        //need to go with 2 pointers
        //start of positive nums

        int negativeArrayStartPointer = 0;

        int startOfPositiveNums = 0;
        int endOfPositiveNums = positiveNums.size() - 1;

        int endPTraversalPointer = endOfPositiveNums;

        for (int i = 0; i < negativeNums.size(); i++) {
            Integer firstNum = negativeNums.get(i);

            if (positiveNums.get(endOfPositiveNums) < Math.abs(firstNum)) {
                //need to look for another positive num
                endPTraversalPointer = endOfPositiveNums;
                Integer secondNo = positiveNums.get(endPTraversalPointer);
                //need to find 3rd positive.
                if (firstNum + secondNo < 0) {
                    //need to find 3rd no in positive array
                    Integer thirdNum = positiveNums.get(endPTraversalPointer-1);

                } else if (firstNum + secondNo == 0) {

                } else {

                }
            } else {

            }
        }


        return null;
    }

}
