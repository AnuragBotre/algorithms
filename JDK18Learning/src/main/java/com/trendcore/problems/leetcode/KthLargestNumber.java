package com.trendcore.problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 * 215. Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestNumber {

    public static void main(String[] args) {
        testCase(new int[]{3, 2, 1, 5, 6, 4}, 2);
        testCase(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
    }

    private static void testCase(int[] ints, int k) {
        KthLargestNumber kthLargestNumber = new KthLargestNumber();
        System.out.println(kthLargestNumber.findKthLargest(ints, k));
    }

    public int findKthLargest(int[] nums, int k) {

        Arrays.sort(nums);

        return nums[nums.length-k];
    }

    /**
     * This is not correct answer. Above Approach gives correct result
     * Verify solution against that approach
     * @param nums
     * @param k
     * @return
     */
    private int approach1(int[] nums, int k) {
        int kArr[] = new int[k];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                fillAllElementsWith(nums[i], kArr);
            } else {
                if (kArr[0] < nums[i]) {
                    shifArr(kArr);
                    kArr[0] = nums[i];
                }
            }
        }

        return kArr[k-1];
    }

    private void shifArr(int[] kArr) {
        for(int i = kArr.length - 1 ; i > 0; i--){
            kArr[i] = kArr[i-1];
        }
    }

    private void fillAllElementsWith(int i, int[] kArr) {
        for (int j = 0; j < kArr.length; j++) {
            kArr[j] = i;
        }
    }

}
