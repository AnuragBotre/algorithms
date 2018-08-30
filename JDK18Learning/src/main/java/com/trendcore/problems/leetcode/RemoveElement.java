package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/remove-element/description/
 * 27. Remove Element
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [3,2,2,3], val = 3,
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * <p>
 * Note that the order of those five elements can be arbitrary.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 * <p>
 * Confused why the returned value is an integer but your answer is an array?
 * <p>
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 * <p>
 * Internally you can think of this:
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeElement(nums, val);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 */
public class RemoveElement {

    public static void main(String[] args) {

        //int[] nums = {3, 2, 2, 3}; //val 2 - 3
        //int[] nums = {0,1,2,2,3,0,4,2}; //val 2
        testCase(new int[]{3, 2, 2, 3}, 2);
        testCase(new int[]{3, 2, 2, 3}, 3);
        testCase(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2);
        testCase(new int[]{3}, 3);
        testCase(new int[]{2}, 3);
        testCase(new int[]{3, 3}, 5);
        testCase(new int[]{4, 5}, 5);
        testCase(new int[]{4, 4, 3, 4, 5, 5}, 5);
    }

    private static void testCase(int[] nums, int val) {
        RemoveElement r = new RemoveElement();
        //int[] nums = {3,3};
        int i = r.removeElement(nums, val);

        print(nums, i, val);
    }

    private static void print(int[] nums, int len, int val) {
        System.out.println();
        System.out.print(" [" + val + "] " + len + " :- ");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(" " + nums[i]);
        }
    }

    public int removeElement(int[] nums, int val) {

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;

    }

    private int approach2(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0] == val ? 0 : 1;
        }

        //using 2 pointers when element is found replace at last

        int last = nums.length - 1;

        boolean containsVal = false;

        for (int i = 0; i <= last; i++) {
            if (nums[i] == val) {
                //get element

                boolean found = false;

                for (int j = last; j > i; j--) {
                    if (nums[j] != val) {
                        nums[i] = nums[j];
                        nums[j] = val;
                        last = j;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    last = i;
                }

                containsVal = true;
            }
        }

        if (containsVal) {
            return last;
        } else {
            return nums.length;
        }
    }

    private int bruteForce(int[] nums, int val) {
        int noOfVals = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                int j;
                for (j = i; j < nums.length; j++) {
                    if (nums[j] != val) {

                        nums[i] = nums[j];
                        nums[j] = val;

                        noOfVals++;
                        break;
                    }
                }

            } else {
                noOfVals++;
            }
        }

        return noOfVals;
    }

}
