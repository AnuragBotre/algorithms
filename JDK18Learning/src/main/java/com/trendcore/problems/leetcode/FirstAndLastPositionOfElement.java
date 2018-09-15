package com.trendcore.problems.leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * <p>
 * 34. Find First and Last Position of Element in Sorted Array
 * <p>
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FirstAndLastPositionOfElement {

    public static void main(String[] args) {

        testCase(new int[]{5, 7, 7, 8, 8, 10}, 8);
        testCase(new int[]{5, 7, 7, 8, 8, 10}, 6);
        testCase(new int[]{1}, 1);
        testCase(new int[]{1, 2, 3}, 2);
    }

    private static void testCase(int[] nums, int target) {
        FirstAndLastPositionOfElement f = new FirstAndLastPositionOfElement();
        int[] ints = f.searchRange(nums, target);
        for (int i : ints) {
            System.out.print(i);
        }
        System.out.println();
    }

    public int[] searchRange(int[] nums, int target) {

        //use two pointers

        int i = 0;
        int startingPointer = 0;
        int lastPointer = nums.length - 1;

        int leftSideStartPos = -1;
        int rightSideStartPos = -1;

        boolean firstLasPos = false;
        boolean firstStartPos = false;

        int currentPosFirstHalf = -1;
        int currentPosSecondHalf = -1;

        for (; startingPointer <= lastPointer; startingPointer++, lastPointer--) {
            if (nums[startingPointer] == target) {
                if (!firstStartPos) {
                    leftSideStartPos = startingPointer;
                    firstStartPos = true;
                }
                currentPosFirstHalf = startingPointer;
            }

            if (nums[lastPointer] == target) {
                if (!firstLasPos) {
                    rightSideStartPos = lastPointer;
                    firstLasPos = true;
                }
                currentPosSecondHalf = lastPointer;
            }
        }

        //print(leftSideStartPos, currentPosFirstHalf, currentPosSecondHalf, rightSideStartPos);

        int firstNo = -1;
        if (firstStartPos) {
            firstNo = leftSideStartPos;
        } else if (currentPosFirstHalf > -1) {
            firstNo = currentPosFirstHalf;
        } else if (currentPosSecondHalf > -1) {
            firstNo = currentPosSecondHalf;
        } else {
            firstNo = rightSideStartPos;
        }

        int secondNo = -1;
        if (firstLasPos) {
            secondNo = rightSideStartPos;
        } else if (currentPosSecondHalf > -1) {
            secondNo = currentPosSecondHalf;
        } else if(currentPosFirstHalf > -1) {
            secondNo = currentPosFirstHalf;
        } else {
            secondNo = leftSideStartPos;
        }


        return new int[]{firstNo, secondNo};
    }

    private void print(Object... args) {
        for (Object arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }


    //This approach was considered but may be for certail inputs may give higher time complexity.
    public int[] searchRangeLeetCodeOptimisticSolution(int[] nums, int target) {
        int left = Arrays.binarySearch(nums, target);
        if(left < 0) return new int[] {-1, -1};
        int right = left;
        while(left - 1 >= 0 && nums[left-1] == target) left--;
        while(right +1 < nums.length && nums[right+1] == target) right++;
        return new int[] {left, right};
    }

    //Not working..
    private int[] approach1(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        } else if (nums.length == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }

        //get one of the index using binary search
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        boolean found = false;

        Set<Integer> treeSet = new TreeSet();

        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                treeSet.add(mid);
                found = true;

            }
            if (nums[low] == target) {
                treeSet.add(low);
                found = true;
                low++;
            }
            if (nums[high] == target) {
                treeSet.add(high);
                found = true;
                high--;
            }

            if (found) {
                found = false;
                continue;
            }

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (!treeSet.isEmpty()) {
            Object[] objects = treeSet.toArray();
            return new int[]{(int) objects[0], (int) objects[objects.length - 1]};
        } else {
            return new int[]{-1, -1};
        }
    }

}
