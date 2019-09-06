package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * <p>
 * 88. Merge Sorted Array
 * <p>
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * <p>
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n)
 * to hold additional elements from nums2.
 * Example:
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int j = 0;
        int i = 0;

        int emptySpaceStart = m;

        int temp = 0;

        for (; i < nums1.length; ) {

            //If elements are present in empty space
            if (emptySpaceStart != m) {

                //if element in empty space last ele is less than num 2 then swap with num2
                if (nums1[emptySpaceStart-1] < nums2[j]) {
                    int t = nums2[j];
                    nums2[j] = nums1[emptySpaceStart-1];
                    nums1[emptySpaceStart-1] = t;
                } else {
                    if (nums1[i] > nums2[j]) {
                        //then num1 -> i needs to be moved to empty space
                        //num2 -> j element  moved to num1 -> i
                        int t = nums1[i];
                        nums1[i] = nums2[j];
                        i++;
                        j++;

                        nums1[emptySpaceStart] = t;
                        emptySpaceStart++;

                    } else {
                        i++;
                    }
                }

            } else {
                if (nums1[i] > nums2[j]) {
                    //then num1 -> i needs to be moved to empty space
                    //num2 -> j element  moved to num1 -> i
                    int t = nums1[i];
                    nums1[i] = nums2[j];
                    i++;
                    j++;

                    nums1[emptySpaceStart] = t;
                    emptySpaceStart++;

                } else {
                    i++;
                }
            }

        }

        //do we need to sort between i and last elem.


    }
}
