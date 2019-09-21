package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * <p>
 * 78. Subsets
 * <p>
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> finalList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> t = new ArrayList<>();
            List<List<Integer>> lists = combineWithOthers(i, nums, t);
            finalList.addAll(lists);
        }

        finalList.add(new ArrayList<>());

        return finalList;
    }

    private List<List<Integer>> combineWithOthers(int i, int[] nums,
                                                  List<List<Integer>> finalList) {
        if (i >= nums.length) {
            return finalList;
        }

        if (finalList.isEmpty()) {
            List list = new ArrayList();
            list.add(nums[i]);
            finalList.add(list);
        } else {
            List<List<Integer>> newFinalList = new ArrayList(finalList);
            for (List list : finalList) {
                List t =new ArrayList(list);
                t.add(nums[i]);
                newFinalList.add(t);
            }
            finalList = newFinalList;
        }

        return combineWithOthers(i + 1, nums, finalList);
    }

}
