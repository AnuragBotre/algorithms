package com.trendcore.problems.leetcode;

import sun.plugin.javascript.navig.Array;

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
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        System.out.println(t.bruteForce(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(t.bruteForce(new int[]{5, 4, 9}));
        System.out.println(t.bruteForce(new int[]{5, 4, 9, -9, 0}));
        System.out.println(t.bruteForce(new int[]{0,3,0,1,1,-1,-5,-5,3,-3,-3,0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        //using 2 pointer and hashmap
        Arrays.sort(nums);
        Map map = new HashMap();

        for (int i = 0; i < nums.length - 1; i++) {
            int r = nums[i] + nums[i + 1];
            if (r <= 0) {
                //put this in negative no
                if(map.containsKey(r)){
                    List o = (List) map.get(r);
                    List tt = new ArrayList();
                    tt.add(nums[i]);
                    tt.add(nums[i+1]);
                    o.add(tt);
                }else{
                    List o = new ArrayList();
                    List tt = new ArrayList();
                    tt.add(nums[i]);
                    tt.add(nums[i+1]);
                    o.add(tt);
                    map.put(r,o);
                }
            } else {
                //put this in positive array

                int result = 0 - nums[i];
                if (map.containsKey(result)) {
                    System.out.println(nums[i] + " " + map.get(result));
                }
            }
        }

        return bruteForce(nums);
        //return mainList;
        //return null;
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
                list.remove((Object)e);
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
