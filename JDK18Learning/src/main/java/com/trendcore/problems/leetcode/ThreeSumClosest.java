package com.trendcore.problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/3sum-closest/description/
 * 16.
 * 3Sum Closest
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest t = new ThreeSumClosest();
        System.out.println(t.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(t.threeSumClosest(new int[]{1, 1, 1, 0}, -100));
    }

    //Approach - 1 brute force

    //Approach - 2 sort array
    public int threeSumClosest(int[] nums, int target) {

        if (nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int low = 0;
        int high = nums.length;
        int mid = (low + high) / 2;

        //need to find target fits in which region
        boolean flag = true;
        while (flag) {
            if (low == high) {
                flag = false;
                break;
            }

            if (nums[mid] == target) {
                break;
            } else if (target < nums[mid]) {
                low = 0;
                high = mid;
                mid = (low + high) / 2;
            } else {
                low = mid + 1;
                high = high;
                mid = (low + high) / 2;
            }
        }

        //try to create window near mid
        if (mid - 1 < 0) {
            //at first
            int i = nums[mid] + nums[mid + 1] + nums[mid + 2];
            System.out.println("result = " + i);
        } else if (mid + 1 > nums.length) {
            //at last
            int i = nums[mid] + nums[mid - 1] + nums[mid - 2];
            System.out.println("result = " + i);
        } else {
            //there is chance of sliding
            int r1 = nums[mid - 1] + nums[mid] + nums[mid + 1];
            //can we slide to right
            if (mid + 2 < nums.length) {
                int r2 = nums[mid] + nums[mid + 1] + nums[mid + 2];
                if(closer(r2,r1,target)){
                    r1 = r2;
                }
            }

            if (mid - 2 < nums.length) {
                int r3 = nums[mid - 1] + nums[mid - 2] + nums[mid];
                if(closer(r3,r1,target)){
                    r1 = r3;
                }
            }
            System.out.println("result = " + r1);
            //can we slide to left
        }



        return bruteForce(nums, target);
    }

    //Correct Algo
    private int bruteForce(int[] nums, int target) {
        Integer closest = null;

        Map map = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int result = nums[i] + nums[j] + nums[k];

                    if (closest == null) {
                        closest = result;
                    } else {
                        if (closer(result, closest, target)) {
                            closest = result;
                        }
                    }
                }
            }
        }

        return closest;
    }

    private boolean closer(int result, int closest, int target) {
        int a = target - result;
        int b = target - closest;
        return Math.abs(a) < Math.abs(b);
    }

}
