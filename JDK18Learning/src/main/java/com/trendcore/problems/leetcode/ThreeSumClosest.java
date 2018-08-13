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
        printResult(new int[]{-1, 2, 1, -4}, 1);
        /*printResult(new int[]{1, 1, 1, 0}, -100);
        printResult(new int[]{-1000, -900, -1, -2, 1, 4, 5}, -100);
        printResult(new int[]{0, 0, 0}, 1);
        printResult(new int[]{1, 1, -1}, 0);

        printResult(new int[]{0, 2, 1, -3}, 1);

        printResult(new int[]{-100,-99,-98,-95}, -101);
        printResult(new int[]{-100,-99,-98,-95}, 101);*/
    }

    private static void printResult(int[] nums, int target) {
        ThreeSumClosest t = new ThreeSumClosest();
        System.out.println(t.threeSumClosest(nums, target) + " " + t.bruteForce(nums, target));
    }


    //Approach-3
    //traverse through mid of sorted array
    //2 pointer approach
    //from the centre one will go in left and other will go to right
    //need to decide how pointer should proceed

    //target  = first closet no
    //a = target - first closet no
    //b = first - a

    //Use division approach
    //target / 3
    //then search closest number
    //after substract target - found number
    //substracted no  / 2

    //Try with Binary Tree
    //keep track of positions for duplicates
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int a = target - (target / 3);

        Object t[] = getClosestNumber(nums, a);
        int pos1 = (int) t[1];
        int firstNo = (int) t[0];

        int c = (target - firstNo) / 2;

        int[] closestNumber = getClosestNumber(nums, c, new int[]{pos1, -1}, 1);
        int secondNo = closestNumber[0];
        int pos2 = closestNumber[1];

        int thirdNoToFind = target - (firstNo + secondNo);
        closestNumber = getClosestNumber(nums, thirdNoToFind, new int[]{pos1, pos2}, 2);

        int thirdNumber = closestNumber[0];

        return firstNo + secondNo + thirdNumber;
    }

    //Need to correct the binary search algo
    //Target binary search algo problem first then will solve this problem.
    private int[] getClosestNumber(int[] nums, int c, int[] position, int posLen) {
        int low = 0;
        int high = nums.length - posLen;
        int mid = (low + high) / 2;

        //need to find target fits in which region
        boolean flag = true;
        while (flag) {
            if (low >= high) {
                flag = false;
                break;
            }

            if (nums[mid] == c && notInPosition(mid, position)) {
                break;
            } else if (c < nums[mid]) {
                high = mid;
                mid = ((low + high) / 2) + 1;
            } else {
                low = mid;
                mid = ((low + high) / 2) + 1;
            }
        }

        if (mid == nums.length) {
            //find correct mid
            mid = nums.length - 1;

            if (mid == position[0] || mid == position[1]) {
                mid = nums.length - 2;
            }

            if (mid == position[0] || mid == position[1]) {
                mid = nums.length - 3;
            }

        }
        return new int[]{nums[mid], mid};
    }

    private boolean notInPosition(int mid, int[] position) {
        for (int i = 0; i < position.length; i++) {
            if (mid == position[i]) {
                return false;
            }
        }
        return true;
    }

    private Object[] getClosestNumber(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        int mid = (low + high) / 2;

        //need to find target fits in which region
        boolean flag = true;
        while (flag) {
            if (low >= high) {
                flag = false;
                break;
            }

            if (nums[mid] == target) {
                break;
            } else if (target < nums[mid]) {
                high = mid;
                mid = (low + high) / 2;
            } else {
                low = mid;
                mid = (low + high) / 2;
            }
        }

        if (mid == nums.length) {
            mid = nums.length - 1;
        }
        return new Object[]{nums[mid], mid};
    }

    //Approach - 2 sort array
    //This approach has a flaw
    //It will not work for this input 0,2,1,-3 , target = 1
    private int approach2(int[] nums, int target) {
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

        if (mid == nums.length) {
            mid = nums.length - 1;
        }

        //try to create window near mid
        if (mid - 1 < 0) {
            //at first
            return nums[mid] + nums[mid + 1] + nums[mid + 2];
        } else if (mid + 1 >= nums.length) {
            //at last
            return nums[mid] + nums[mid - 1] + nums[mid - 2];
        } else {
            //there is chance of sliding
            int r1 = nums[mid - 1] + nums[mid] + nums[mid + 1];
            //can we slide to right
            if (mid + 2 < nums.length) {
                int r2 = nums[mid] + nums[mid + 1] + nums[mid + 2];
                if (closer(r2, r1, target)) {
                    r1 = r2;
                }
            }

            //can we slide to left
            if (mid - 2 > 0) {
                int r3 = nums[mid - 1] + nums[mid - 2] + nums[mid];
                if (closer(r3, r1, target)) {
                    r1 = r3;
                }
            }

            return r1;
        }
    }

    //Approach - 1 brute force
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
