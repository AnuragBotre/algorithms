package com.trendcore.problems.leetcode;

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
        System.out.println(t.threeSumClosest(new int[]{1,1,1,0}, -100));
    }

    //Approach - 1 brute force

    //Approach - 2 sort array

    public int threeSumClosest(int[] nums, int target) {

        Integer closest = null;

        Map map = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i+1; j < nums.length - 1; j++) {
                for(int k = j+1 ; k < nums.length;k++){
                    int result = nums[i] + nums[j] + nums[k];

                    if(closest == null){
                        closest = result;
                    }else{
                        if(closer(result,closest,target)){
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
