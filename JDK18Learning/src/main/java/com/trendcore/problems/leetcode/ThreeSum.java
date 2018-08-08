package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/3sum/description/
 * <p>
 * 15. 3Sum
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        System.out.println(t.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(t.threeSum(new int[]{5, 4, 9}));
        System.out.println(t.threeSum(new int[]{5, 4, 9, -9, 0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        //using 2 pointer and hashmap
        Map map = new HashMap<>();

        List mainList = new ArrayList();

        for (int i = 0; i < nums.length - 1; i++) {

            map.put(nums[i],0);
            map.put(nums[i+1],0);

            int result = 0 - (nums[i] + nums[i + 1]);
            if (map.containsKey(result)) {
                List list = new ArrayList();
                list.add(nums[i]);
                list.add(nums[i+1]);
                list.add(result);
                mainList.add(list);
            }else{

            }
        }

        return bruteForce(nums);
        //return mainList;
    }

    private List<List<Integer>> bruteForce(int[] nums) {
        int firstPointer = 0;
        int secondPointer = 0;

        List mainList = new ArrayList();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List list = new ArrayList();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if (!containsTriplet(mainList, list)) {
                            mainList.add(list);
                        }
                    }
                }
            }

        }

        return mainList;
    }

    private boolean containsTriplet(List<List<Integer>> mainList, List<Integer> list) {
        for (List elements : mainList) {
            if (isEqual(elements, list)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqual(List<Integer> elements, List<Integer> list) {

        for (Integer e : elements) {
            if (list.contains(e)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
