package com.trendcore.problems.leetcode;

import java.util.*;

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
 * <p>
 * solution is present
 * https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        System.out.println(t.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(t.threeSum(new int[]{}));
        //Not working for this input
        System.out.println(t.threeSum(new int[]{1, 2, -2, -1}));
        System.out.println(t.threeSum(new int[]{5, 4, 9}));
        System.out.println(t.threeSum(new int[]{5, 4, 9, -9, 0}));
        System.out.println(t.threeSum(new int[]{0, 3, 0, 1, 1, -1, -5, -5, 3, -3, -3, 0}));
        System.out.println(t.threeSum(new int[]{-7, -11, 12, -15, 14, 4, 4, 11, -11, 2, -8, 5, 8, 14, 0, 3, 2, 3, -3, -15, -2, 3, 6, 1, 2, 8, -5, -7, 3, 1, 8, 11, -3, 6, 3, -4, -13, -15, 14, -8, 2, -8, 4, -13, 13, 11, 5, 0, 0, 9, -8, 5, -2, 14, -9, -15, -1, -6, -15, 9, 10, 9, -2, -8, -8, -14, -5, -14, -14, -6, -15, -5, -7, 5, -11, 14, -7, 2, -9, 0, -4, -1, -9, 9, -10, -11, 1, -4, -2, 2, -9, -15, -12, -4, -8, -5, -11, -6, -4, -9, -4, -3, -7, 4, 9, -2, -5, -13, 7, 2, -5, -12, -14, 1, 13, -9, -3, -9, 2, 3, 8, 0, 3}));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        Map keys = new HashMap();
        List list = new ArrayList();

        for (int i = 0; i < nums.length - 1; i++) {
            Map map = new HashMap();
            for (int j = i + 1; j < nums.length; j++) {
                int result = 0 - (nums[i] + nums[j]);
                if (map.containsKey(result)) {
                    List l = new ArrayList();
                    l.add(result);
                    l.add(nums[i]);
                    l.add(nums[j]);
                    String key = getKey(l);
                    if (!keys.containsKey(key)) {
                        keys.put(key, 0);
                        list.add(l);
                    }

                } else {
                    map.put(nums[j], 0);
                }
            }
        }

        return list;
    }

    private String getKey(List l) {
        Collections.sort(l);
        String s = "";
        for (Object o : l) {
            s = s + o;
        }
        return s;
    }


    private Object[] mergeResult(int r, List<Integer> k) {
        List l = new ArrayList();
        String key = "";
        boolean rAdded = false;
        for (Integer i : k) {
            if (i < r) {
                l.add(i);
                key = key + i;
            } else {
                if (!rAdded) {
                    l.add(r);
                    rAdded = true;
                    key = key + r;
                }
                l.add(i);
                key = key + i;

            }
        }
        if (!rAdded) {
            l.add(r);
            key = key + r;
        }
        return new Object[]{l, key};
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
            if (isEqual(elements, new ArrayList<>(list))) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqual(List<Integer> elements, List<Integer> list) {

        for (Integer e : elements) {
            if (list.contains(e)) {
                list.remove((Object) e);
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
