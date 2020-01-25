package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * <p>
 * 80. Remove Duplicates from Sorted Array II
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Given nums = [0,0,1,1,1,1,2,3,3],
 * <p>
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
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
 * int len = removeDuplicates(nums);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 */
public class RemoveDuplicatesFromSortedArrayII {

    public int removeDuplicates(int[] nums) {

        int lastPos = 0;
        int i = 0;
        int count = 0;
        boolean duplicateInitilized = false;
        int pointer = 0;
        boolean duplicate = false;
        int lengthOfArray = 1;
        boolean duplicateShift = false;
        boolean pointerInitialized = false;

        for (; i < nums.length; i++) {

            if (i == 0) {
                lastPos = i;
                count = 1;
                duplicate = false;
                duplicateInitilized = false;
            } else {
                if (nums[lastPos] == nums[i]) {
                    count++;
                    if (count > 2 && !duplicateInitilized) {
                        //pointer to starting position of the duplicate start
                        if (!pointerInitialized) {
                            pointer = i;
                        }
                        duplicate = true;
                        duplicateInitilized = true;
                    } else {
                        if (!duplicateInitilized) {
                            lengthOfArray++;
                        }
                    }
                } else {
                    //do we need to copy the element

                    count = 1;
                    duplicateInitilized = false;

                    if (duplicate) {
                        int temp = nums[pointer];
                        nums[pointer] = nums[i];
                        nums[i] = temp;
                        lastPos = pointer;
                        pointer++;
                        duplicateShift = true;
                        pointerInitialized = true;
                    } else if (duplicateShift) {
                        int temp = nums[pointer];
                        nums[pointer] = nums[i];
                        nums[i] = temp;
                        lastPos = pointer;
                        pointer++;
                        pointerInitialized = true;
                    }
                    duplicate = false;
                    lengthOfArray++;
                }
            }
        }


        //try with available gap algo
        /**
         * if(count > 2)
         *      then we need to shift
         *      calculate available positions while doing skipping more than 2 elements
         * else
         *      gap is available then move current element inwards
         *
         *
         * 1 , 1 , 1 , 1 , 1 , 2 , 2 , 3
         * considering gap is size 3 after completing 1.
         * 1 , 1 , 1 , 1 , 1
         * gap -> 1 , 1 , 1 size 3
         * start element shifting till elements can be moved while shifting
         * if found other set of common elements then againg increase the gap
         */

        return lengthOfArray;
    }

    private int approach1(int[] nums) {
        int lastPos = 0;
        int count = 0;

        int lengthOfArray = nums.length;

        //0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4

        for (int i = 0; i < lengthOfArray; i++) {
            if (i == 0) {
                lastPos = i;

            } else {
                if (nums[lastPos] == nums[i]) {
                    count++;

                    if (count > 1) {
                        //shift array
                        int j = i;
                        int noOfElementsToBeShifted = 0;
                        int endOfArray = lengthOfArray;
                        for (; j < endOfArray; j++) {
                            if (nums[j] != nums[lastPos]) {
                                break;
                            }
                            noOfElementsToBeShifted++;
                            lengthOfArray--;
                        }

                        int k;
                        int c = 0;
                        for (k = i; c < nums.length; k++, j++, c++) {
                            if (j >= nums.length) {
                                break;
                            }
                            nums[k] = nums[j];
                        }

                        lastPos = i;
                        count = 0;
                    }
                } else {
                    lastPos = i;
                    count = 0;
                }
            }
        }

        return lengthOfArray;
    }

}
